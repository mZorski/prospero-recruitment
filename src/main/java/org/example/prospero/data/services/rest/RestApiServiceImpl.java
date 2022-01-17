package org.example.prospero.data.services.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.example.prospero.data.services.exception.RestApiFetchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RestApiServiceImpl implements RestApiService {

	private final OkHttpClient okHttpClient;
	private final JsonMapper jsonMapper;

	@Override
	public <T> T fetchData(String url, Class<T> tClass) {
		try {
			var bodyData = fetchData(url);
			return jsonMapper.readValue(bodyData, tClass);
		} catch (IOException e) {
			throw new RestApiFetchException(e);
		}
	}

	@Override
	public <T> T fetchData(String url, TypeReference<T> typeReference) {
		try {
			var bodyData = fetchData(url);
			return jsonMapper.readValue(bodyData, typeReference);
		} catch (IOException e) {
			throw new RestApiFetchException(e);
		}
	}

	private InputStream fetchData(String url) throws IOException {
		var request = new Request.Builder()
			.url(url)
			.addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.build();

		var response = okHttpClient.newCall(request).execute();
		var body = response.body();
		if (response.isSuccessful() && body != null) {
			return body.byteStream();
		}

		throw new RestApiFetchException();
	}
}
