package com.hospitaltask.repository;

import com.hospitaltask.entity.Availity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailabilityRepo extends JpaRepository<Availity,Long> {
}
