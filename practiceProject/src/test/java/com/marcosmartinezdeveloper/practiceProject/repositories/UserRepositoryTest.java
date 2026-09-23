package com.marcosmartinezdeveloper.practiceProject.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import com.marcosmartinezdeveloper.practiceProject.persistence.entities.User;
import com.marcosmartinezdeveloper.practiceProject.persistence.repositories.UserRepository;

@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	private String username;
	private String email;
	private String password;
	private String role;
	
	public UserRepositoryTest() {
		this.username="testingUsername";
		this.email="testing@email.com";
		this.password="teST1235679.";
		role="CUSTOMER";
	}

	//It's totaly unnecesary to test JPA implementated methods. But it is usefull as a practice
	@Test
	public void findByUsernameTest() {
		User insertedUser = insertOneUser();
		User userFounded = userRepository.findByUsername(username).orElse(null);
		assertThat(insertedUser.equals(userFounded));
	}
	
	public User insertOneUser() {
		User user = new User().builder()
				.username(username)
				.email(email)
				.password(password)
				.role(role)
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.build();
		return userRepository.save(user);
	}
}
