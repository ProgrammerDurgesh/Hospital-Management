package com.hospitaltask.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class SaveSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String startTime;
	private String endTime;
}
