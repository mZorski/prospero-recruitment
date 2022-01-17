package org.example.prospero.data.services.faker;

import org.example.prospero.data.domain.enums.Gender;
import org.example.prospero.data.dto.PersonDto;

import java.time.LocalDate;

public interface FakerApiService {

	PersonDto fetchPersonData(Gender gender);
}
