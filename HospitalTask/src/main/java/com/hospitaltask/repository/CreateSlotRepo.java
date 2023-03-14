package com.hospitaltask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hospitaltask.entity.SuperSlot;

@Repository
public interface CreateSlotRepo extends JpaRepository<SuperSlot, Long> {

    @Query(value = "select start_time from super_slot", nativeQuery = true)
    String getStartTime();

    @Query(value = "select end_time from super_slot", nativeQuery = true)
    String getEndTime();

    @Query(value = "select duration_minutes from super_slot", nativeQuery = true)
    String getDuration();

    @Query(value = "select * from super_slot order by id desc limit 1", nativeQuery = true)
    SuperSlot getLastInsertedValue();
}
