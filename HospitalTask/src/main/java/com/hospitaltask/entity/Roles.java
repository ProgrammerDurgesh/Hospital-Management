package com.hospitaltask.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Roles {
    @SuppressWarnings("unused")
	private final boolean flag = true;
    @Column(name = "role_created")
    private final Date createdDate = Calendar.getInstance().getTime();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_Id")
    private long id;
    @Column(nullable = false, length = 30, unique = true, updatable = true)
    private String roleName;
    @Column(nullable = true, length = 200, unique = false, updatable = true)
    private String roleDescription;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	public boolean isFlag() {
		return flag;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
    
    
    
}
