package com.cooksys.friendlr.person;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("person")
public class PersonController {

	private PersonService personService;

	public PersonController(PersonService personSerivce) {
		// TODO Auto-generated constructor stub
		this.personService = personSerivce;
	}
	
	// Get /person
	@GetMapping
	public List<PersonDto> getAll(@RequestParam(required=false) Long id, @RequestParam(required=false) String firstName, @RequestParam(required=false) String lastName) {
		return personService.find(firstName, lastName);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PersonDto> getSpecificPerson(@PathVariable Long id) throws NotFoundException {
		return personService.get(id);
	}
	
	@PostMapping
	public ResponseEntity<PersonDto> addPerson(@RequestBody(required=false) Person person) throws NotFoundException {
		return personService.add(person);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<PersonDto> editPerson(@PathVariable Long id, @RequestBody(required=false) Person person) throws NotFoundException {
		return personService.edit(person);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePerson(@PathVariable Long id) throws NotFoundException {
		return personService.delete(id);
	}
	
	
}
