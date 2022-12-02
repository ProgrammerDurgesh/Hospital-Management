package com.hospitaltask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="tbl_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roles
{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="role_Id")
    private long id;
    @Column(nullable=false,length=30,unique=true,updatable=true)
        private String roleName;
    @Column(nullable=true,length=200,unique=false,updatable=true)
    private String roleDescription;

    @Column(name="role_created")
    private final Date
            createdDate = Calendar.getInstance().getTime();


}
