package com.hospitaltask.entity;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "tbl_superUser")
public class SuperAdmin {
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected final Date creationDate = Calendar.getInstance().getTime();
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModifiedDate = Calendar.getInstance().getTime();
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

	@ManyToOne
	private Roles roles;

	private long CreatedBy;

	private boolean flag = true;

	private String token;
	private Boolean isActive = false;
	private String ConfirmationToken;
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	public long getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(long createdBy) {
		CreatedBy = createdBy;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getConfirmationToken() {
		return ConfirmationToken;
	}
	public void setConfirmationToken(String confirmationToken) {
		ConfirmationToken = confirmationToken;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	
	
	
	

}
