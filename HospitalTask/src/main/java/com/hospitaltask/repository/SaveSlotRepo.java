package com.hospitaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospitaltask.entity.Slots;

public interface SaveSlotRepo extends JpaRepository<Slots,Integer>{

}
