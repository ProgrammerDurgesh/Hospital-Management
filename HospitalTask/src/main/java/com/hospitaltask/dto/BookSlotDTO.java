package com.hospitaltask.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookSlotDTO {
	private Long id;
    private LocalDate date;
    private int doctorId;
    private int slotId;
   

}
