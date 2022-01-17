package org.example.prospero.data.services.zipcode;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.example.prospero.data.services.rest.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ZipcodeServiceImpl implements ZipcodeService {

	public static final String ZIPCODE_API_BASE_URL = "http://kodpocztowy.intami.pl/city/";

	private final RestApiService restApiService;

	@Override
	public List<String> fetchZipcodes(String city) {
		if(city == null || city.isBlank()) {
			return Collections.emptyList();
		}

		var url = UriComponentsBuilder
			.fromUriString(ZIPCODE_API_BASE_URL)
			.pathSegment(city.toUpperCase(Locale.ROOT))
			.build().toString();

		return restApiService.fetchData(url, new TypeReference<>() {
		});
	}
}
