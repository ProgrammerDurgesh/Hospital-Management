package com.hospitaltask.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitaltask.dto.SlotDto;
import com.hospitaltask.entity.SuperSlot;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.CreateSlotService;

@RestController
@RequestMapping("/slot")
public class OneTimeSlotSaveController {

    @Autowired
    private CreateSlotService createSlotService;
    
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @NotNull SlotDto slotDto) {
        try {
            createSlotService.createSlot();
           /* System.out.println(superSlots.size());
            if(!superSlots.isEmpty()) {
                for (int i = 0; i < superSlots.size(); i++) {
                    if (slotDto.getDurationMinutes().equals(superSlots.get(i).getDurationMinutes())) {
                        return CustomResponseHandler.response("Already Exist ", HttpStatus.BAD_REQUEST, slotDto.getDurationMinutes());
                    } else {
                        createSlotService.save(slotDto);
                        createSlotService.createSlot();
                    }

                }
            }
            else {
                createSlotService.save(slotDto);
                createSlotService.createSlot();
                return CustomResponseHandler.response("Record Save", HttpStatus.CREATED, slotDto);

            }*/

            return CustomResponseHandler.response("Record Save", HttpStatus.CREATED, slotDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CustomResponseHandler.response("Invalid Record", HttpStatus.INTERNAL_SERVER_ERROR, slotDto);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<SuperSlot> all = null;
        try {
            all = createSlotService.getAll();
            return CustomResponseHandler.response("Total Record      " + all.size(), HttpStatus.OK, all);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CustomResponseHandler.response("Record Not found   :    " + 0, HttpStatus.NOT_FOUND, null);
    }
}
