package org.example.prospero.web.controller;

import lombok.RequiredArgsConstructor;
import org.example.prospero.data.dto.RegistrationDto;
import org.example.prospero.data.services.user.UserService;
import org.example.prospero.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class Controller {

	private final UserService userService;


	@PostMapping("/register")
	public void register(@RequestBody @Valid RegistrationDto registrationDto, BindingResult bindingResult) {
		userService.registerUser(registrationDto);
	}

	@GetMapping("/users")
	@ResponseBody
	public List<UserDto> getUsers() {
		return userService.findAllUsers().stream().map(UserDto::new).collect(Collectors.toList());
	}

	@GetMapping("/user/{login}")
	@ResponseBody
	public UserDto getUser(@PathVariable("login") String login) {
		return userService.findByLogin(login).map(UserDto::new).orElse(null);
	}

}
