package com.hospitaltask.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Slots {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String startTime;
	private String endTime;

	private String durationTime;
}
