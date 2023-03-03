package com.hospitaltask.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitaltask.dto.BookSlotDTO;
import com.hospitaltask.entity.BookSlot;
import com.hospitaltask.entity.SaveSlot;
import com.hospitaltask.repository.BookSlotRepo;
import com.hospitaltask.repository.SaveSlotRepo;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.BookSlotService;

@Service
public class BookSlotImpl extends CustomResponseHandler implements BookSlotService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private BookSlotRepo bookSlotRepo;

	@Autowired
	private SaveSlotRepo saveSlotRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	BookSlot dtoToDoctor(BookSlotDTO bookSlotDTO) {
		return this.modelMapper.map(bookSlotDTO, BookSlot.class);
	}

	@Override
	public String save(BookSlotDTO bookSlotDTO) {

		try {
			BookSlot dtoToDoctor = dtoToDoctor(bookSlotDTO);
			dtoToDoctor.setStatus("Pending");
			BookSlot save = bookSlotRepo.save(dtoToDoctor);
			System.out.println(save);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "save";
	}

	@Override
	public List<?> availableSlot(LocalDate date) {
		int bookslotindex=0;
		List<SaveSlot> list= saveSlotRepo.findAll();
		System.out.println("Starting List Size    :          "+list.size());
		List<SaveSlot> finalShowList=new ArrayList<>();
		List<BookSlot> bookSlots = bookSlotRepo.getBookedSlotByDate(date);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("save slot size :  "+list.size());
			System.out.println("bookSlots size :  "+bookSlots.size());
			
			Long doctorId = bookSlots.get(bookslotindex).getSlotId();
			System.out.println("book slot ID     : "+doctorId);
			System.out.println("save slot id     :    "+list.get(i).getId());
			if(list.get(i).getId()==doctorId)
			{
				bookslotindex++;
				if(bookslotindex==bookSlots.size())bookslotindex=bookslotindex-1;
				
			}
			else
			{
				finalShowList.add( list.get(i));
			}
			/*
			 * long slotId=bookSlots.get(0).getId(); System.out.println(slotId);
			 * bookSlots.get(i).getDate(); bookSlots.get(i).getDoctorId(); for (Doctor value
			 * : doctors) { long doctor = value.getDoctorId(); if (doctorId == doctor) {
			 * list.remove(i); doctors.remove(value); }
			 *
			}*/

			
		}

		return finalShowList;
	}

	@Override
	public void booked(long slotId,int STATUS) {

		// find slot by doctor
		BookSlot bookedSlotByDoctorId = bookSlotRepo.findById(slotId).orElse(null);
		final String status[] = { "Accept", "Reject", "Pending" };
		if (STATUS == 0) {
			BookSlot bookSlot = bookedSlotByDoctorId;
			bookSlot.setACCEPT(true);
			bookSlot.setISTRUE(true);
			bookSlot.setStatus(status[0]);
			bookSlotRepo.save(bookSlot);
		}
		if (STATUS == 1) {
			BookSlot bookSlot = bookedSlotByDoctorId;
			bookSlot.setACCEPT(false);
			bookSlot.setISTRUE(false);
			bookSlot.setStatus(status[1]);
			bookSlotRepo.save(bookSlot);
		}

	}

	@Override
	public List<BookSlot> getPendingSlot(int doctorId) {
		List<BookSlot> bookedSlotByDoctorId = null;
		try {
			bookedSlotByDoctorId = bookSlotRepo.getBookedSlotByDoctorId(doctorId);
			return bookedSlotByDoctorId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookedSlotByDoctorId;
	}

	@Override
	public List<BookSlot> getRejectedSlot(int doctorId) {
		List<BookSlot> rejectedSlot = null;
		try {
			rejectedSlot = bookSlotRepo.getRejectedSlot(doctorId);
			return rejectedSlot;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rejectedSlot;
	}

	@Override
	public List<BookSlot> getAccepedSlot(int doctorId) {
		List<BookSlot> acceptedSlot = null;
		try {
			acceptedSlot = bookSlotRepo.getAccepedSlot(doctorId);
			return acceptedSlot;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acceptedSlot;
	}

}
