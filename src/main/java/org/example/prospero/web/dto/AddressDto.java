package org.example.prospero.web.dto;

import org.example.prospero.data.domain.Address;

public record AddressDto(String street, String buildingNumber, String city, String zipcode) {

	public AddressDto(Address address) {
		this(address.getStreet(), address.getBuildingNumber(), address.getCity(), address.getZipcode());
	}
}
