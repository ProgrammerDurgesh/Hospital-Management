package com.hospitaltask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/HM")
public class HomeController {

	@GetMapping("/login")
	String homePage()
	{
		return "Login.html";
	}

}
