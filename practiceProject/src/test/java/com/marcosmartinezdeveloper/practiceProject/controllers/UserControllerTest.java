package com.marcosmartinezdeveloper.practiceProject.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.marcosmartinezdeveloper.practiceProject.facade.dtos.LoginDTO;
import com.marcosmartinezdeveloper.practiceProject.facade.dtos.SignUpDTO;
import com.marcosmartinezdeveloper.practiceProject.facade.services.JwtService;
import com.marcosmartinezdeveloper.practiceProject.facade.services.UserService;
import com.marcosmartinezdeveloper.practiceProject.persistence.entities.User;
import com.marcosmartinezdeveloper.practiceProject.persistence.repositories.UserRepository;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@MockitoBean
    private UserService userService;
	
	@MockitoBean
    private JwtService jwtService;
    
	@MockitoBean
    private AuthenticationManager authenticationManager;
	
//	It is used in the SignUpValidator
	@MockitoBean
    public UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;
    
	static String username;
	static String password;
	static String email;
	static String role;
    
	@TestConfiguration
    static class TestSecurityConfig {

        @Bean
        SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .authorizeHttpRequests(auth -> auth
                            .anyRequest().permitAll()
                    )
                    .build();
        }
    }
	
	@BeforeAll
	public static void setUp() {
		
		username = "testUsername";
		password = "aWell_formedPassword1";
		email = "testEmail@gmail.com";
		role = "CUSTOMER";
	}

    @Test
    void loginShouldReturnLoginView() throws Exception {
    	LoginDTO expedtedLoginDTO = new LoginDTO();

        mockMvc.perform(get("/users/login")
        		.with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("users/login"))
                .andExpect(model().attribute("loginDTO", expedtedLoginDTO));
    }

    @Test
    void signUpShouldReturnSignUpView() throws Exception {
    	SignUpDTO expectedSignUpDTO = new SignUpDTO();

        mockMvc.perform(get("/users/signUp")
        		.with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("users/sign_up"))
                .andExpect(model().attribute("signUpDTO", expectedSignUpDTO));
    }

    @Test
    void postSignUpShouldRedirectLogin() throws Exception {
    	SignUpDTO signUpDTO = new SignUpDTO(username, password, password, email);
    	User user = new User(null, username, password, email, role);

    	when(userService.save(signUpDTO)).thenReturn(user);
    	when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
    	when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        mockMvc.perform(post("/users/signUp")
	                .param("username", username)
	                .param("password", password)
	                .param("confirmationPassword", password)
	                .param("email", email)
        		.with(csrf()))
		        .andExpect(status().is3xxRedirection())
		        .andExpect(redirectedUrl("/users/login"));
    }

    @Test
    void postSignUpShouldReturnWithValidationErrors() throws Exception {

        mockMvc.perform(post("/users/signUp")
	                .param("username", username)
	                .param("password", password)
	                .param("confirmationPassword", password+"3")
	                .param("email", email)
        		.with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("users/sign_up"))
        .andExpect(model().attributeHasFieldErrors("signUpDTO", "confirmationPassword"));
    }
    
    
}
