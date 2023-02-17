package com.hospitaltask.repository;

import com.hospitaltask.entity.CreateSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateSlotRepo extends JpaRepository<CreateSlot, Long> {

    @Query(value = "select start_time from create_slot", nativeQuery = true)
    String getStartTime();

    @Query(value = "select end_time from create_slot", nativeQuery = true)
    String getEndTime();

    @Query(value = "select duration_minutes from create_slot", nativeQuery = true)
    String getDuration();
}