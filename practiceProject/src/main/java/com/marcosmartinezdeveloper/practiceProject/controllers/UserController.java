package com.marcosmartinezdeveloper.practiceProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcosmartinezdeveloper.practiceProject.ConstantsAndEnums;
import com.marcosmartinezdeveloper.practiceProject.facade.dtos.LoginDTO;
import com.marcosmartinezdeveloper.practiceProject.facade.dtos.SignUpDTO;
import com.marcosmartinezdeveloper.practiceProject.facade.services.JwtService;
import com.marcosmartinezdeveloper.practiceProject.facade.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
    
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("loginDTO", new LoginDTO());
		return "users/login";
	}

	@PostMapping("/login")
	public String postLogin(@Valid @ModelAttribute LoginDTO loginDTO, BindingResult result,
	        HttpServletRequest request,
	        HttpServletResponse response) {
		boolean correctCredentials = userService.logIn(loginDTO);
		if(correctCredentials) {
	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
	            );
	            if (authentication.isAuthenticated()) {
	                String token =jwtService.generateToken(loginDTO.getUsername());
	                
	                Cookie cookie = new Cookie(ConstantsAndEnums.COOKIE_NAME, token);
	                cookie.setHttpOnly(true);
	                cookie.setSecure(false); // true with HTTPS
	                cookie.setPath("/");
	                cookie.setMaxAge(60 * 60); // 1 hour

	                response.addCookie(cookie);

	                return "redirect:/";
	            } else {
	                throw new UsernameNotFoundException("Invalid user request!");
	            }
		}else {
			result.addError(new ObjectError("loginDTO", "Invalid user or password"));
			return "users/login";
		}
	}

	@GetMapping("/signUp")
	public String getSignUp(Model model) {
		model.addAttribute("signUpDTO", new SignUpDTO());
		return "users/sign_up";
	}

	@PostMapping("/signUp")
	public String postSignUp(@Valid @ModelAttribute SignUpDTO signUpDTO, BindingResult result){
	    if (result.hasErrors()) {
	        return "users/sign_up";
	    }
	    userService.save(signUpDTO);
	    
	    return null;
    }


}
