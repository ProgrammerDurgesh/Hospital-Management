package com.hospitaltask.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitaltask.entity.Slots;
import com.hospitaltask.repository.SaveSlotRepo;
import com.hospitaltask.service.SaveSlotService;
@Service
public class SaveSlotServiceImpl implements SaveSlotService {

	@Autowired
	private SaveSlotRepo repo;

	@Override
	public List<Slots> getAll() {
		List<Slots> list=repo.findAll();
		return list;
	}

}
