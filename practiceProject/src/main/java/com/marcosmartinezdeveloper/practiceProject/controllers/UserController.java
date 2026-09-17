package com.marcosmartinezdeveloper.practiceProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcosmartinezdeveloper.practiceProject.facade.dtos.LoginDTO;

@Controller
@RequestMapping("/users")
public class UserController {

	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("loginDTO", new LoginDTO());
		return "users/login";
	}

}
