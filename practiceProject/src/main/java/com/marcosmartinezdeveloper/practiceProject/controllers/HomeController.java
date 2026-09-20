package com.marcosmartinezdeveloper.practiceProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String getLogin(Model model) {
		return "home";
	}

}
