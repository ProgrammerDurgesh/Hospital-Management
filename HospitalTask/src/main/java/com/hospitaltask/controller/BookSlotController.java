package com.hospitaltask.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitaltask.dto.BookSlotDTO;
import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.SaveSlot;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.SaveSlotRepo;
import com.hospitaltask.service.BookSlotService;

@RestController
@RequestMapping("/slot")
public class BookSlotController {

	@Autowired
	private DoctorRepo doctorRepo;

	@Autowired
	private SaveSlotRepo saveSlotRepo;

	@Autowired
	private BookSlotService bookSlotService;

	@PostMapping("/book")
	public String save(@RequestBody BookSlotDTO bookSlotDTO) {

		try {

			bookSlotService.save(bookSlotDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(bookSlotDTO.getSlotId());
		System.out.println(bookSlotDTO.getDoctorId());
		System.out.println(bookSlotDTO.getDate());
		System.out.println(bookSlotDTO.isTrue());
		return "save Method";
	}

	@GetMapping("/d-Drop-down")
	public void getAllDocotor() {
		List<Doctor> doctors = doctorRepo.findAll();
		List<String> doctorShow = new ArrayList<>();
		for (int i = 0; i < doctors.size(); i++) {
			
			 boolean bookSlots=doctorShow.add(doctors.get(i).getDoctorName());
			System.out.println(bookSlots);
		}
	}

	@GetMapping("/s-Drop-down")
	public void getAllSlot() {

		List<SaveSlot> getSlot = saveSlotRepo.findAll();
		List<String> slotShow = new ArrayList<>();
		for (int i = 0; i < getSlot.size(); i++) {
			slotShow.add(getSlot.get(i).getStartTime() + "      -    " + getSlot.get(i).getEndTime());
			System.out.println(slotShow);
		}
	}
}
