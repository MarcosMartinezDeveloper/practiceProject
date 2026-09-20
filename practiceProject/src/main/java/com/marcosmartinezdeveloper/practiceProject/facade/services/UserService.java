package com.marcosmartinezdeveloper.practiceProject.facade.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.marcosmartinezdeveloper.practiceProject.facade.dtos.LoginDTO;
import com.marcosmartinezdeveloper.practiceProject.facade.dtos.SignUpDTO;
import com.marcosmartinezdeveloper.practiceProject.facade.mappers.SignUpDTOMapperImpl;
import com.marcosmartinezdeveloper.practiceProject.persistence.entities.User;
import com.marcosmartinezdeveloper.practiceProject.persistence.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService{

	private UserRepository userRepository;
	
	private SignUpDTOMapperImpl signupDTOmapper;
	
	private final PasswordEncoder passwordEncoder;
	
	public User save(SignUpDTO signUpDTO) {
		User user = signupDTOmapper.toCustomerEntity(signUpDTO);
		user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
		user = userRepository.save(user);
		return user;
	}
	
	public boolean logIn(LoginDTO loginDTO) {
		boolean correctCredentials = false;

		if(StringUtils.hasLength(loginDTO.getUsername()) && StringUtils.hasLength(loginDTO.getPassword())) {
			User user = userRepository.findByUsername(loginDTO.getUsername()).orElse(null) ;
			if(user == null) {
				return false;
			}
			correctCredentials = passwordEncoder.matches(
					loginDTO.getPassword(),
					user.getPassword());
		}
		
		return correctCredentials;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(() ->
        new UsernameNotFoundException(
                "User not found with email: " + username));
		return (UserDetails) user;
	}
}
