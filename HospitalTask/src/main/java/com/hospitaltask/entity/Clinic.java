package com.hospitaltask.entity;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_clinic")

public class Clinic
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="clinic_Id")
    private  Long id;
    @Column(nullable = false, length = 45)
    private  String  clinicName;
    @NotNull
    private String  clinicAddress;
    private final boolean flag=true;
    @Column(name="state",nullable = true, unique = false, length = 45)
    private String clinicState;

    @Column(name="clinic_created")
    private final  Date createdDate = Calendar.getInstance().getTime();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public String getClinicAddress() {
		return clinicAddress;
	}

	public void setClinicAddress(String clinicAddress) {
		this.clinicAddress = clinicAddress;
	}

	public String getClinicState() {
		return clinicState;
	}

	public void setClinicState(String clinicState) {
		this.clinicState = clinicState;
	}

	public boolean isFlag() {
		return flag;
	}

	public Date getCreatedDate() {
		return createdDate;
	}


}
