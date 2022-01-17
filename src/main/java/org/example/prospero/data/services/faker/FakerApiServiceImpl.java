package org.example.prospero.data.services.faker;

import lombok.RequiredArgsConstructor;
import org.example.prospero.data.domain.enums.Gender;
import org.example.prospero.data.dto.FakerApiPersonResponseDto;
import org.example.prospero.data.dto.PersonDto;
import org.example.prospero.data.services.rest.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FakerApiServiceImpl implements FakerApiService {

	public static final String FAKER_API_BASE_URL = "https://fakerapi.it/api/v1/";

	public static final String LOCALE_NAME = "_locale";
	public static final String LOCALE_VALUE = "pl_PL";

	public static final String QUANTITY_NAME = "_quantity";
	public static final int QUANTITY_VALUE = 1;

	public static final String SEED_NAME = "_seed";
	public static final Integer SEED_VALUE = 1;

	public static final String GENDER_NAME = "_gender";

	private final RestApiService restApiService;


	@Override
	public PersonDto fetchPersonData(Gender gender) {
		var url = UriComponentsBuilder
			.fromUriString(FAKER_API_BASE_URL)
			.path("persons")
			.queryParam(LOCALE_NAME, LOCALE_VALUE)
			.queryParam(QUANTITY_NAME, QUANTITY_VALUE)
			.queryParam(SEED_NAME, SEED_VALUE)
			.queryParam(GENDER_NAME, gender)
			.build().toString();

		return restApiService.fetchData(url, FakerApiPersonResponseDto.class).getData().get(0);
	}
}
