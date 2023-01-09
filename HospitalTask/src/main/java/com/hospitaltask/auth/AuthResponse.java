package com.hospitaltask.auth;

public class AuthResponse {
    private String accessToken;

    public AuthResponse() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
