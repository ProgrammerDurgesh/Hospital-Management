package com.hospitaltask.serviceImpl;

import com.hospitaltask.dto.CreateSlotDto;
import com.hospitaltask.entity.CreateSlot;
import com.hospitaltask.repository.CreateSlotRepo;
import com.hospitaltask.service.CreateSlotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateSlotImp implements CreateSlotService {

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private CreateSlotRepo createSlotRepo;


    CreateSlot dtoToSlot(CreateSlotDto createSlotDto)
    {
        return  modelMapper.map(createSlotDto, CreateSlot.class);
    }


    @Override
    public CreateSlot save(CreateSlotDto createSlotDto) {
        return createSlotRepo.save(dtoToSlot(createSlotDto));
    }

    @Override
    public CreateSlot update(Long id, String duration) {
        //TODO....update
        return null;
    }

    @Override
    public CreateSlot updateByStartTime(Long id, String startTime) {
        //TODO....updateByStartTime
        return null;
    }

    @Override
    public CreateSlot updateByEndTime(Long id, String endTime) {
        //TODO....updateByEndTime
        return null;
    }

    @Override
    public CreateSlot updateAll(Long id, String startTime, String endTime, String duration) {
        //TODO updateAll
        return null;
    }

    @Override
    public CreateSlot delete(Long id) {
        //TODO Delete
        createSlotRepo.deleteById(id);
        return null;
    }

    @Override
    public List<CreateSlot> getAll() {
        return createSlotRepo.findAll();
    }
}
