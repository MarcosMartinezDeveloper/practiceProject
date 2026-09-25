package com.marcosmartinezdeveloper.practiceProject.facade.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.marcosmartinezdeveloper.practiceProject.facade.dtos.SignUpDTO;
import com.marcosmartinezdeveloper.practiceProject.facade.mappers.SignUpDTOMapperImpl;
import com.marcosmartinezdeveloper.practiceProject.persistence.entities.User;
import com.marcosmartinezdeveloper.practiceProject.persistence.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@Mock
	private SignUpDTOMapperImpl signupDTOmapper;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private UserService userService;
	
	static String username;
	static String password;
	static String email;
	static String role;
	
	@BeforeAll
	public static void setUp() {
		
		username = "testUsername";
		password = "aWell_formedPassword1";
		email = "aValid@gmail.com";
		role = "CUSTOMER";
	}
	
	@Test
	public void saveTest() {
		SignUpDTO signUpDTO = new SignUpDTO(username, password, password, email);
		User user = new User(null, username, password, email, role);

		given(signupDTOmapper.toCustomerEntity(signUpDTO)).willReturn(user);
		given(passwordEncoder.encode(password)).willReturn(password);
		given(userRepository.save(user)).willReturn(user);
		
		User newUser = userService.save(signUpDTO);

		assertThat(newUser).isNotNull();
		assertThat(newUser).isEqualTo(user);
	}
	
//	@Test
//	public void logIn() {
//		
//	}
//	
//	@Test
//	public void loadUserByUsername() {
//		
//	}

}
