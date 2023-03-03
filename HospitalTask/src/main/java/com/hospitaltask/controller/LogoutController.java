package com.hospitaltask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    @GetMapping("/logout")
    public ResponseEntity<?> logout()
    {
      //  Marker requestId = MarkerManager.getMarker(PasswordManager.generateUuid());

        return null;
    }
}
