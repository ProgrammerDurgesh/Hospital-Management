package com.hospitaltask.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tbl_otp")
public class OtpVerify {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String otp;
    @NotNull
    private LocalDateTime otpDateTime;
    private Long userId;
    private Long UserRole;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public LocalDateTime getOtpDateTime() {
		return otpDateTime;
	}
	public void setOtpDateTime(LocalDateTime otpDateTime) {
		this.otpDateTime = otpDateTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserRole() {
		return UserRole;
	}
	public void setUserRole(Long userRole) {
		UserRole = userRole;
	}
    
}
