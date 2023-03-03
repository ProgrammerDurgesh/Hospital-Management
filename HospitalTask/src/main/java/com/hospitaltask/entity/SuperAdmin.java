package com.hospitaltask.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_superUser")
public class SuperAdmin {
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected final Date creationDate= Calendar.getInstance().getTime();
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedDate=Calendar.getInstance().getTime();
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
    private boolean flag = true;
    @ManyToOne
    private Roles roles;
    private Long CreatedBy= 0L;

    private String token;
    private Boolean isActive;

}

