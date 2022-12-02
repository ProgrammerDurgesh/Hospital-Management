package com.hospitaltask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping ("/")
    public String home()
        {
            return "<H1>Welcome To Home Page</h1>";
        }
    @GetMapping("/admin")
    public String homeAdmin()
        {

            return "<H1>Welcome To Admin Page</h1>";
        }
    @GetMapping("/user")
    public String homeUser()
        {

            return "<H1>Welcome To User Page</h1>";
        }


}
