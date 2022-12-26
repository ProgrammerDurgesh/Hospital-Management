package com.hospitaltask.entity;

public class LoginResponse {
    private final String JWT;

    public LoginResponse(String JWT) {
        this.JWT = JWT;
    }


    public String getJWT() {
        return JWT;
    }
}
