package com.hospitaltask.controller;

import com.hospitaltask.jwt.JwtUtil;
import org.apache.logging.log4j.MarkerManager;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/logout")
    public ResponseEntity<?> logout()
    {
      //  Marker requestId = MarkerManager.getMarker(PasswordManager.generateUuid());

        return null;
    }
}
