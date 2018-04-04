package com.cooksys.friendlr.person;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {
	
	Person toPerson(PersonDto dto);
	
	@Mapping(target="ids", source="friends")
	PersonDto toDto(Person person);
	
	default Long fromPerson(Person pers) {
		return pers.getId();
	}
}
