package org.example.prospero.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FakerApiResponseDto<T> {

	private String status;
	private int code;
	private int total;
	List<T> data;
}
