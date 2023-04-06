package com.hospitaltask.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class AvailityDto {

	
	private LocalDate date;
	private String startTime;
	private String  endTime;
	private int slot;

}
