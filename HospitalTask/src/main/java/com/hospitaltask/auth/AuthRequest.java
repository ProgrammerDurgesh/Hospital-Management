package com.hospitaltask.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthRequest {
   private String userName;
   private String password;
    public AuthRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
