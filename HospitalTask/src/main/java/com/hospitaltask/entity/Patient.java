package com.hospitaltask.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "tbl_Patient")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Column(name = "patient_admitted_Date")
    private final Date createdDate = Calendar.getInstance().getTime();
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NotNull
    @Column(name = "patientEmail")
    private String email;
    @NotNull
    private String password, name, age, bloodGroup, illness;
    private boolean flag = true;

    private String token;

    @Column(name = "TIMESTAMP")
    private LocalDateTime tokenCreationDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles roles;

}
