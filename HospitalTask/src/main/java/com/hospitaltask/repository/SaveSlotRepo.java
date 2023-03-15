package com.hospitaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospitaltask.entity.Slots;

@Repository
public interface SaveSlotRepo extends JpaRepository<Slots,Integer>{

}
