package org.example.prospero.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

	private int id;
	private String street;
	private String streetName;
	private String buildingNumber;
	private String city;
	private String zipcode;
}
