package com.marcosmartinezdeveloper.practiceProject.controllers;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.marcosmartinezdeveloper.practiceProject.facade.services.JwtService;
import com.marcosmartinezdeveloper.practiceProject.facade.services.UserService;

@WebMvcTest(RestUserController.class)
public class RestUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
	private JwtService jwtService;

    @MockitoBean
	private UserService userService;
    
	static String username;
	static String password;
	static String secretNumber;
	static String loginPath = "/rest-users/login";
	
	@TestConfiguration
    static class TestSecurityConfig {

        @Bean
        SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
            return http
                    .csrf(csrf -> csrf.disable())
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
		secretNumber = "3546461172897459F423F48761198642621655468576D5A1678158AB71347437";
	}
    
    @Test
    void loginReturnBearerTokenWhenCredentialsAreCorrect() throws Exception{
        String jsonLoginDTO = String.format("""
        {
            "username": "%s",
            "password": "%s"
        }
        """, username, password);
        
    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

    	Authentication authentication = mock(Authentication.class);

        when(authenticationManager.authenticate(usernamePasswordAuthenticationToken))
        .thenReturn(authentication);

		mockMvc.perform(
		        post(loginPath)
		            .contentType(MediaType.APPLICATION_JSON)
		            .content(jsonLoginDTO)
		    )
		    .andExpect(status().isOk())
		    .andExpect(content().string(startsWith("Bearer ")));
    }
    
    @Test
    void loginReturn401WhenCredentialsAreInvalid() throws Exception {

        String jsonLoginDTO = String.format("""
        {
            "username": "%s",
            "password": "%s"
        }
        """, username, password);
        
    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);


        when(authenticationManager.authenticate(usernamePasswordAuthenticationToken))
        .thenThrow(new BadCredentialsException("Bad credentials"));

        mockMvc.perform(
                post(loginPath)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonLoginDTO)
            )
            .andExpect(status().isUnauthorized());
    }

}
