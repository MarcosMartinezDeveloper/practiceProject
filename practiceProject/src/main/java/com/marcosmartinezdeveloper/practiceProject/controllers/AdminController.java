package com.marcosmartinezdeveloper.practiceProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marcosmartinezdeveloper.practiceProject.facade.dtos.LoginDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
	

//	@GetMapping("/test")
//	public String getLogin(Model model) {
//		model.addAttribute("loginDTO", new LoginDTO());
//		return "admin/test_admin";
//	}


}
