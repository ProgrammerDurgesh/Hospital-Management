package com.hospitaltask.dto;

import com.hospitaltask.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
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
