package com.hospitaltask.service;

import java.time.LocalDate;
import java.util.List;

import com.hospitaltask.dto.BookSlotDTO;
import com.hospitaltask.entity.BookSlot;

public interface BookSlotService {
	
	String save(BookSlotDTO bookSlotDTO);
	void booked(long slotId,int status);
	List<BookSlot> getPendingSlot(int doctorId);
	List<BookSlot> getRejectedSlot(int doctorId);
	List<BookSlot> getAccepedSlot(int doctorId);
	List<?> availableSlot(LocalDate date);
}
