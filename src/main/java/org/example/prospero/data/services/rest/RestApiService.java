package org.example.prospero.data.services.rest;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;

public interface RestApiService {

	<T> T fetchData(String url, Class<T> tClass);

	<T> T fetchData(String url, TypeReference<T> typeReference);
}
