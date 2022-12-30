package com.hospitaltask.payloads;

public class AuthResponse {

    private String email;
    private String number;
    private String accessToken;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public AuthResponse() { }
    public AuthResponse(String email,String number, String accessToken) {
        this.email = email;
        this.number= number;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


}
