package com.hospitaltask.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitaltask.entity.SaveSlot;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.SaveSlotService;


@RestController
@RequestMapping("/saveslot")
public class SavesSlotController {
	
	@Autowired
	private SaveSlotService saveSlotService;
	
	
	
	@GetMapping("/all")
	public  ResponseEntity<?>  getAll()
	{
		List<SaveSlot> all = saveSlotService.getAll();
		if(all.size()!=0)
		{
			return CustomResponseHandler.response("Record", HttpStatus.OK, all);
		}
		else
		{
			return CustomResponseHandler.response("Record Not Found", HttpStatus.NOT_FOUND, all);
 
		}
	}

}
