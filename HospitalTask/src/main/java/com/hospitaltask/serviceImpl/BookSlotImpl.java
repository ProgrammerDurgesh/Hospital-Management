package com.hospitaltask.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitaltask.dto.BookSlotDTO;
import com.hospitaltask.entity.BookSlot;
import com.hospitaltask.entity.Doctor;
import com.hospitaltask.repository.BookSlotRepo;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.BookSlotService;

@Service
public class BookSlotImpl extends CustomResponseHandler implements BookSlotService {

	private static final long serialVersionUID = 1L;
	@Autowired
	private DoctorRepo doctorRepo;
	@Autowired
	private BookSlotRepo bookSlotRepo;

	@Autowired
	private ModelMapper modelMapper;

	BookSlot dtoToDoctor(BookSlotDTO bookSlotDTO) {
		return this.modelMapper.map(bookSlotDTO, BookSlot.class);
	}

	@Override
	public String save(BookSlotDTO bookSlotDTO) {
		
		List<?> list=availableSlot(bookSlotDTO);
		System.out.println(list);
		
		try {
			BookSlot bookSlot = dtoToDoctor(bookSlotDTO);
			System.out.println(bookSlot);
			Long id = (long) bookSlotDTO.getDoctorId();
			Doctor doctor = doctorRepo.findById(id).orElse(null);
			System.out.println("Doctor ID :             " + doctor.getDoctorId());
			BookSlot save = bookSlotRepo.save(dtoToDoctor(bookSlotDTO));
			System.out.println(save);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "save";
	}

	public List<?> availableSlot(BookSlotDTO bookSlotDTO) {
		
		List<BookSlot> bookSlots=bookSlotRepo.findAll();
		List<Doctor> doctors=doctorRepo.findAll();
		List<Doctor> doctors2=new ArrayList<>();
		
		for (int i = 0; i < bookSlots.size(); i++) {
			Long doctorId = bookSlots.get(i).getDoctorId();
			bookSlots.get(i).getDate();
			bookSlots.get(i).getDoctorId();
			for (int j = 0; j < doctors.size(); j++) {
				long doctor=doctors.get(j).getDoctorId();
				if(doctorId==doctor)
				{
					doctors2.add(doctors.get(i));
					break;
				}
			}
			
		}
		
		return null;
	}

}
