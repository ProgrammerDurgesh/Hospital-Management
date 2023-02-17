package com.hospitaltask.controller;

import com.hospitaltask.dto.CreateSlotDto;
import com.hospitaltask.entity.CreateSlot;
import com.hospitaltask.response.CustomResponseHandler;
import com.hospitaltask.service.CreateSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/slot")
public class SlotController {

    @Autowired
    private CreateSlotService createSlotService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @NotNull CreateSlotDto createSlotDto) {
        try {
            createSlotService.save(createSlotDto);
            return CustomResponseHandler.response("Record Save", HttpStatus.CREATED, createSlotDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CustomResponseHandler.response("Invalid Record", HttpStatus.INTERNAL_SERVER_ERROR, createSlotDto);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<CreateSlot> all = null;
        try {
            all = createSlotService.getAll();
            return CustomResponseHandler.response("Total Record      " + all.size(), HttpStatus.OK, all);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CustomResponseHandler.response("Record Not found   :    " + 0, HttpStatus.NOT_FOUND, null);
    }

}
