package com.marcosmartinezdeveloper.practiceProject.facade.dtos;

import com.marcosmartinezdeveloper.practiceProject.controllers.validators.projectAnnotations.PasswordsMatch;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@PasswordsMatch
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

	@NotBlank(message = "Username is required")
    @Size(min = 5, max = 20, message = "Username must have between 5 and 20 characters")
    private String username;
	
	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must have at least 8 characters")
	@Pattern(
	    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[^A-Za-z0-9]).*$",
	    message = "Password must contain at least one uppercase letter, one lowercase letter and one special character"
	)
	private String password;

    @NotBlank(message = "Password confirmation is required")
    private String confirmationPassword;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email must be a valid value")
	private String email;
}
