package com.marcosmartinezdeveloper.practiceProject.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.marcosmartinezdeveloper.practiceProject.facade.dtos.LoginDTO;
import com.marcosmartinezdeveloper.practiceProject.facade.services.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/rest-users")
public class RestUserController {
	
	private JwtService jwtService;
    
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginDTO loginDTO) {
		try {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
	            );

    		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
    				.commaSeparatedStringToAuthorityList("ROLE_CUSTOMER");
    		
    		String token = Jwts
    				.builder()
    				.setId("softtekJWT")
    				.setSubject(loginDTO.getUsername())
    				.claim("authorities",
    						grantedAuthorities.stream()
    								.map(GrantedAuthority::getAuthority)
    								.collect(Collectors.toList()))
    				.setIssuedAt(new Date(System.currentTimeMillis()))
    				.setExpiration(new Date(System.currentTimeMillis() + 600000))
    				.signWith(SignatureAlgorithm.HS512,
    						jwtService.SECRET.getBytes()).compact();

    		return "Bearer " + token;
		} catch (BadCredentialsException e) {
		    throw new ResponseStatusException(
		            HttpStatus.UNAUTHORIZED,
		            "Invalid username or password"
		    );
		}
		
	}


}
