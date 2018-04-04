package com.cooksys.friendlr.person;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	private List<Person> personz = new ArrayList<>();
	private PersonMapper mapper;
	
	public PersonService(PersonMapper mapper) {
		this.mapper = mapper;
		
		Person will = new Person(0L, "Will", "Marttala", personz);
		Person michael = new Person(1L, "Michael", "Boren", personz);
		Person ham = new Person(2L, "Hamilton", "Spivey", personz);
		
		personz.add(will);
		personz.add(michael);
		personz.add(ham);
		
	}
	
	public List<PersonDto> find(String firstName, String lastName) {
		// TODO Auto-generated method stub
		List<Person> persons = personz;
		
		if(firstName != null) {
			persons = persons.stream().filter(personz -> firstName.equals(personz.getFirstName())).collect(Collectors.toList());
		}
		
		if(lastName != null) {
			persons = persons.stream().filter(personz -> lastName.equals(personz.getFirstName())).collect(Collectors.toList());
		}
		
		return persons.stream().map(mapper::toDto).collect(Collectors.toList());
	}

	public ResponseEntity<PersonDto> get(Long id) {
		// TODO Auto-generated method stub
		if(id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			try {
				return new ResponseEntity<PersonDto>(mapper.toDto(personz.get(Math.toIntExact(id))), HttpStatus.OK);
			} catch (IndexOutOfBoundsException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}

	public ResponseEntity<PersonDto> add(Person person) {
		// TODO Auto-generated method stub
		if(person.getId() == null ) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else  {
			try {
				
				person.setFirstName(person.getFirstName());
				person.setLastName(person.getLastName());
				person.setFriends(personz);
				person.setId(person.getId()); 
				personz.add(person);
		
				return new ResponseEntity<PersonDto>(mapper.toDto(person), HttpStatus.CREATED);
			
			} catch (IndexOutOfBoundsException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}

	public ResponseEntity<PersonDto> edit(Person person) {
		// TODO Auto-generated method stub
		
		if(person.getId() != null) {
			try {
				person.setFirstName(person.getFirstName());
				person.setLastName(person.getLastName());
				person.setId(person.getId());
				person.setFriends(personz);
				personz.set(Math.toIntExact(person.getId()), person);
			
				return new ResponseEntity<PersonDto>(mapper.toDto(person), HttpStatus.OK);
			} catch (IndexOutOfBoundsException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> delete(Long id) {
		// TODO Auto-generated method stub
		
		if(id == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			try {
				personz.remove(personz.get(Math.toIntExact(id)));
				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (IndexOutOfBoundsException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	}

}
