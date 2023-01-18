package com.hospitaltask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("")
	String homePage() {
		System.out.println("this is login Page ");
		return "Home";
	}
@GetMapping("/login")
	String login() {
		System.out.println("this is login Page ");
		return "login";
	}

	@GetMapping("/create-new")
	String createNew() {
		System.out.println("this is login Page ");
		return "createNew";
	}

}
