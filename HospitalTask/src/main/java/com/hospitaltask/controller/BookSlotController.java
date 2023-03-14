package com.hospitaltask.controller;

import java.time.LocalDate;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitaltask.dto.BookSlotDTO;
import com.hospitaltask.entity.BookSlot;
import com.hospitaltask.entity.Doctor;
import com.hospitaltask.entity.SaveSlot;
import com.hospitaltask.repository.BookSlotRepo;
import com.hospitaltask.repository.DoctorRepo;
import com.hospitaltask.repository.SaveSlotRepo;
import com.hospitaltask.response.CustomResponseHandler;
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

	@Autowired
	private BookSlotRepo bookSlotRepo;


	@PostMapping("/book")
	public String save(@RequestBody BookSlotDTO bookSlotDTO) {
		try {

			bookSlotService.save(bookSlotDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "save Method";
	}

	@GetMapping("/d-Drop-down")
	public void getAllDoctor() {
		List<Doctor> doctors = doctorRepo.findAll();
		System.out.println(doctors);
	}

	// drop down slot Timing
	@GetMapping("/s-Drop-down")
	public List<?> getAllSlot(@NotNull BookSlotDTO bookSlotDTO) {
		List<Long> integers = bookSlotRepo.getBookedSlot(LocalDate.parse("2023-02-28"));
		System.out.println(integers);
		List<SaveSlot> getSlot = saveSlotRepo.findAll();
		System.out.println("Before Remove" + getSlot.size());
		for (int i = 0; i < getSlot.size(); i++) {
			int indexCompare = 0;
			if (getSlot.get(i).getId() == integers.get(indexCompare)) {
				getSlot.remove(i);
				if (indexCompare == integers.size())
					break;
				indexCompare++;
			}
		}
		return getSlot;
	}

	@GetMapping("/booked")
	public void booked() {
		bookSlotService.booked(512, 0);

	}

	@GetMapping("/availableSlot")
	List<?> availableSlot() {
		List<?> availableSlot = bookSlotService.availableSlot(LocalDate.parse("2023-03-01"));
		System.out.println("final List For show :					"+availableSlot.size());
		
		return availableSlot;
	}

	@GetMapping("/pendingSlot")
	public ResponseEntity<?> getPendingSlot(int doctorId) {
		List<BookSlot> pendingSlot = bookSlotService.getPendingSlot(doctorId);
		if (pendingSlot.size() != 0)
			return CustomResponseHandler.response("Total Pending Record : " + pendingSlot.size(), HttpStatus.OK,
					pendingSlot);
		else
			return CustomResponseHandler.response("Record Not Found ", HttpStatus.NOT_FOUND, doctorId);

	}

	@GetMapping("/rejectedSlot")
	public ResponseEntity<?> getRejectedSlot(int doctorId) {
		List<BookSlot> rejectedSlot = bookSlotService.getRejectedSlot(doctorId);
		if (rejectedSlot.size() != 0)
			return CustomResponseHandler.response("Total Pending Record : " + rejectedSlot.size(), HttpStatus.OK,
					rejectedSlot);
		else
			return CustomResponseHandler.response("Record Not Found ", HttpStatus.NOT_FOUND, doctorId);

	}

	@GetMapping("/acceptedSlot")
	public ResponseEntity<?> getAcceptedSlot(int doctorId) {
		List<BookSlot> accepedSlot = bookSlotService.getAccepedSlot(doctorId);
		if (accepedSlot.size() != 0)
			return CustomResponseHandler.response("Total Pending Record : " + accepedSlot.size(), HttpStatus.OK,
					accepedSlot);
		else
			return CustomResponseHandler.response("Record Not Found ", HttpStatus.NOT_FOUND, doctorId);

	}

}
