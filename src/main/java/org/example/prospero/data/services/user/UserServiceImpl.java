package org.example.prospero.data.services.user;

import lombok.RequiredArgsConstructor;
import org.example.prospero.data.domain.Address;
import org.example.prospero.data.domain.Person;
import org.example.prospero.data.domain.User;
import org.example.prospero.data.dto.RegistrationDto;
import org.example.prospero.data.pesel.PeselParser;
import org.example.prospero.data.repository.UserRepository;
import org.example.prospero.data.services.faker.FakerApiService;
import org.example.prospero.data.services.zipcode.ZipcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Validated
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {

	public static final String DEFAULT_ZIPCODE = "99-999";
	public static final String PHONE_NUMBER_DELIMITER = "-";

	private final UserRepository userRepository;
	private final FakerApiService fakerApiService;
	private final ZipcodeService zipcodeService;

	@Override
	public void registerUser(@Valid RegistrationDto registrationDto) {

		PeselParser.Pesel pesel = null;
		try {
			pesel = PeselParser.parse(registrationDto.getPesel());
		} catch (ParseException ignored) {
			throw new IllegalArgumentException("Pesel could not be parsed");
		}

		var personDto = fakerApiService.fetchPersonData(pesel.getGender());
		var zipcode = zipcodeService.fetchZipcodes(personDto.getAddress().getCity())
			.stream()
			.findFirst()
			.orElse(DEFAULT_ZIPCODE);

		var address = new Address();
		address.setStreet(personDto.getAddress().getStreet());
		address.setBuildingNumber(personDto.getAddress().getBuildingNumber());
		address.setCity(personDto.getAddress().getCity());
		address.setZipcode(zipcode);

		var person = new Person();
		person.setFirstName(personDto.getFirstname());
		person.setLastName(personDto.getLastname());
		person.setEmail(personDto.getEmail());
		person.setPhone(formatPhoneNumber(registrationDto.getPhoneNumber()));
		person.setBirthday(pesel.getBirthday());
		person.setGender(pesel.getGender());
		person.setAddress(address);

		var user = new User();
		user.setLogin(registrationDto.getLogin());
		user.setPassword(registrationDto.getPassword());
		user.setPerson(person);

		userRepository.save(user);
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findByLogin(String login) {
		return login != null ? userRepository.findByLogin(login) : Optional.empty();
	}

	private String formatPhoneNumber(String phoneNumber) {
		var digitsOnly = phoneNumber.replaceAll("\\D", "");

		var split = digitsOnly.split(".{3}");
		return String.join(PHONE_NUMBER_DELIMITER, split);
	}
}
