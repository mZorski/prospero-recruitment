package org.example.prospero.web.dto;

import org.example.prospero.data.domain.Person;
import org.example.prospero.data.domain.enums.Gender;

import java.time.LocalDate;

public record PersonDto(String firstName, String lastName, String email, String phone, LocalDate birthday,
						Gender gender, AddressDto addressDto) {

	public PersonDto(Person person) {
		this(person.getFirstName(), person.getLastName(), person.getEmail(), person.getPhone(), person.getBirthday(), person.getGender(), new AddressDto(person.getAddress()));
	}
}
