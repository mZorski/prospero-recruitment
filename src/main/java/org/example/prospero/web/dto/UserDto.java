package org.example.prospero.web.dto;

import org.example.prospero.data.domain.User;

public record UserDto(String login, String password, PersonDto personDto) {

	public UserDto(User user) {
		this(user.getLogin(), user.getPassword(), new PersonDto(user.getPerson()));
	}
}
