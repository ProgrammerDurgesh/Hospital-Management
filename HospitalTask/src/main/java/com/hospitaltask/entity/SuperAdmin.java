package com.hospitaltask.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_superUser")
public class SuperAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "email", unique = true)
    private String email;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    private boolean flag=true;
    @ManyToOne
    private Roles roles;
}

