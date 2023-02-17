package com.hospitaltask.service;

import com.hospitaltask.dto.CreateSlotDto;
import com.hospitaltask.entity.CreateSlot;

import java.util.List;

public interface CreateSlotService {

    CreateSlot save(CreateSlotDto createSlotDto);

    CreateSlot update(Long id, String duration);

    CreateSlot updateByStartTime(Long id, String startTime);

    CreateSlot updateByEndTime(Long id, String endTime);

    CreateSlot updateAll(Long id, String startTime, String endTime, String duration);

    CreateSlot delete(Long id);

    List<CreateSlot> getAll();

}
