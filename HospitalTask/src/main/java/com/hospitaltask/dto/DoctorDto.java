package com.hospitaltask.dto;

import com.hospitaltask.entity.Clinic;
import com.hospitaltask.entity.Roles;
import lombok.*;

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
    private Clinic clinic;
    private Roles roles;

}
