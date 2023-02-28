package com.hospitaltask.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

		/*
		 * List<?> list=availableSlot(bookSlotDTO); System.out.println(list.size()); for
		 * (Object object : list) { System.out.println(object); }
		 */
		try {
			/*
			 * BookSlot bookSlot = dtoToDoctor(bookSlotDTO); System.out.println(bookSlot);
			 * //Long id = (long) bookSlotDTO.getDoctorId(); Doctor doctor =
			 * doctorRepo.findById(id).orElse(null);
			 */
			// System.out.println("Doctor ID : " + doctor.getDoctorId());
			BookSlot save = bookSlotRepo.save(dtoToDoctor(bookSlotDTO));
			System.out.println(save);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "save";
	}

	public List<?> availableSlot(BookSlotDTO bookSlotDTO) {

		List<BookSlot> bookSlots = bookSlotRepo.findAll();
		List<Doctor> doctors = doctorRepo.findAll();
		List<Doctor> doctors2 = new ArrayList<>();
		List<Doctor> doctorsFalse = new ArrayList<>();
		for (int i = 0; i < bookSlots.size(); i++) {
			Long doctorId = bookSlots.get(i).getDoctorId();
			bookSlots.get(i).getDate();
			bookSlots.get(i).getDoctorId();
			for (Doctor value : doctors) {
				long doctor = value.getDoctorId();
				if (doctorId == doctor) {
					doctors.remove(value);
				}
			}

		}

		return doctors;
	}

	@Override
	public void booked(String STATUS) {

		List<BookSlot> bookedSlotByDoctorId = bookSlotRepo.getBookedSlotByDoctorId(1);
		for (int i = 0; i < bookedSlotByDoctorId.size(); i++) {

			final String status[] = { "Accept", "Reject", "Pending" };
			for (int j = 0; j < status.length; j++) {

				if (status[j] == STATUS) {
					try {
						BookSlot findById = bookSlotRepo.findById(513).orElse(null);
						findById.setStatus(STATUS);
						bookSlotRepo.save(findById);
						break;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					

				}

			}
			
		}
	}
	/*
	 * public String status(String STATUS) { final String status[] = { "Accept",
	 * "Reject", "Pending" }; for (int i = 0; i < status.length; i++) {
	 * 
	 * if (status[i] == STATUS) { try { BookSlot findById =
	 * bookSlotRepo.findById(511).orElse(null); findById.setStatus(STATUS);
	 * bookSlotRepo.save(findById); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * } } return STATUS;
	 * 
	 * }
	 */
}
