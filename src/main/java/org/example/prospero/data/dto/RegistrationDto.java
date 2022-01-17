package org.example.prospero.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.prospero.data.validation.constraint.MatchingPassword;
import org.example.prospero.data.validation.constraint.Pesel;
import org.example.prospero.data.validation.constraint.PhoneNumber;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@MatchingPassword
public class RegistrationDto {

	@NotBlank
	@Pattern(regexp = "\\w{5,}")
	private String login;

	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,}$")
	private String password;

	private String confirmPassword;

	@NotBlank
	@PhoneNumber
	private String phoneNumber;

	@NotBlank
	@Pesel
	private String pesel;
}
