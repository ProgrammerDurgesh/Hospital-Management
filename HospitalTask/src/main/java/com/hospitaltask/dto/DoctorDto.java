package com.hospitaltask.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto
{

    private String doctorName;
    private String email;
    private String password;
    private String specialization;
    private String experience;
    private String address;
    private IdentityDTO clinic;
    private IdentityDTO roles;

}
