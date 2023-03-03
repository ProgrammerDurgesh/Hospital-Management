package com.hospitaltask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuperUserDto {
    private String email;
    private String userName;
    private String password;
    private IdentityDTO roles;
}
