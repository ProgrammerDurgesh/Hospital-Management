package com.hospitaltask.controller;

import com.hospitaltask.jwt.JwtUtil;
import com.hospitaltask.response.CustomResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/logout")
    public ResponseEntity<?> logout()
    {
       /* boolean b=jwtUtil.validateToken(null,null);*/
        return null;
    }
}
