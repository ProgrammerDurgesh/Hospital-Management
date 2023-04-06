package com.hospitaltask.entity;



import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

public class BookSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private LocalDate date;
	@NotNull
	private  boolean ISTRUE = true;

	private Long doctorId;

	private Long slotId;;

	private  boolean ACCEPT = false;
	private String status;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public boolean isISTRUE() {
		return ISTRUE;
	}
	public void setISTRUE(boolean iSTRUE) {
		ISTRUE = iSTRUE;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Long getSlotId() {
		return slotId;
	}
	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}
	public boolean isACCEPT() {
		return ACCEPT;
	}
	public void setACCEPT(boolean aCCEPT) {
		ACCEPT = aCCEPT;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
