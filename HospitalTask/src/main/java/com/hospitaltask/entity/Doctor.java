package com.hospitaltask.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_doctor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

	@Column(name = "doctor_joining_date")
	private final Date createdDate = Calendar.getInstance().getTime();
	@Id
	@Column(name = "doctorId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long doctorId;
	private String doctorName;
	@Column(unique = true, nullable = false, length = 35)
	private String email;
	@NotNull
	private String password;
	private boolean flag = true;
	@NotNull
	private String specialization;
	@NotNull
	private String experience;
	@NotNull
	private String address;

	@ManyToOne
	@JoinColumn(name = "clinic_id", nullable = false)
	private Clinic clinic;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false, updatable = true)
	private Roles roles;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModifiedDate = Calendar.getInstance().getTime();

	private String token;
	private Boolean isActive;
	private String CreatedBy;
	 private String confirmationToken;

	
}
