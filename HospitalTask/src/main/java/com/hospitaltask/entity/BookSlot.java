package com.hospitaltask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

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
}
