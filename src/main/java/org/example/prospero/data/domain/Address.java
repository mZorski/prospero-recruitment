package org.example.prospero.data.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address extends BaseEntity {

	@Column(name = "street")
	private String street;

	@Column(name = "buildingNumber")
	private String buildingNumber;

	@Column(name = "city")
	private String city;

	@Column(name = "zipcode")
	private String zipcode;

	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Person person;
}
