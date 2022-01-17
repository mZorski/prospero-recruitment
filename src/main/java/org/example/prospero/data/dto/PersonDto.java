package org.example.prospero.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {

	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private AddressDto address;
}
