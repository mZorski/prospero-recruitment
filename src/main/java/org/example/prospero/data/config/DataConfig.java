package org.example.prospero.data.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class DataConfig {

	@Bean
	public JavaTimeModule javaTimeModule() {
		var javaTimeModule = new JavaTimeModule();
		var localDateDeserializer = new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		javaTimeModule.addDeserializer(LocalDate.class, localDateDeserializer);
		return javaTimeModule;
	}

	@Bean
	public JsonMapper objectMapper(@Autowired JavaTimeModule javaTimeModule) {
		return JsonMapper.builder()
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
			.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.addModule(javaTimeModule)
			.build();
	}

	@Bean
	public OkHttpClient okHttpClient() {
		return new OkHttpClient.Builder().build();
	}
}
