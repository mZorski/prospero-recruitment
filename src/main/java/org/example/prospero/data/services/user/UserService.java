package org.example.prospero.data.services.user;

import org.example.prospero.data.domain.User;
import org.example.prospero.data.dto.RegistrationDto;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface UserService {

	void registerUser(@Valid RegistrationDto registrationDto);

	List<User> findAllUsers();

	Optional<User> findByLogin(String login);
}
