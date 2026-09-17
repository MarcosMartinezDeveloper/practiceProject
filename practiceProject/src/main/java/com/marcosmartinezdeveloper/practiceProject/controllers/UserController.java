package com.marcosmartinezdeveloper.practiceProject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marcosmartinezdeveloper.practiceProject.facade.dtos.LoginDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("loginDTO", new LoginDTO());
		return "users/login";
	}

//	@PostMapping("/login")
//	public String postLogin(@Valid @ModelAttribute LoginDTO loginDTO, BindingResult result){
//		System.out.println("entrando en el post");
//	    if (result.hasErrors()) {
//			System.out.println("tiene errores");
//	        return "users/login";
//	    }
//		System.out.println("Yendo a null");
//	    
//	    return null;
//    }


}
