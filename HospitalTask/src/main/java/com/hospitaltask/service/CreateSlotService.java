package com.hospitaltask.service;

import com.hospitaltask.dto.CreateSlotDto;
import com.hospitaltask.entity.SuperSlot;

import java.util.List;

public interface CreateSlotService {

    SuperSlot save(CreateSlotDto createSlotDto);

    SuperSlot update(Long id, String duration);

    SuperSlot updateByStartTime(Long id, String startTime);

    SuperSlot updateByEndTime(Long id, String endTime);

    SuperSlot updateAll(Long id, String startTime, String endTime, String duration);

    SuperSlot delete(Long id);

    List<SuperSlot> getAll();


    public int createSlot();

}
