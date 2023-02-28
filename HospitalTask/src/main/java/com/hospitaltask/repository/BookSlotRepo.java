package com.hospitaltask.repository;

import com.hospitaltask.entity.BookSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookSlotRepo extends JpaRepository<BookSlot,Integer> {
    @Query(value = "select slot_id  from book_slot where date=:date",nativeQuery = true)
    List<Long> getBookedSlot(LocalDate date);

    @Query(value = "select * from book_slot where doctor_id=:id",nativeQuery = true)
    List<BookSlot> getBookedSlotByDoctorId(int id);
}
