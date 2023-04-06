package com.hospitaltask.repository;

import com.hospitaltask.entity.UpdatePasswordByLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UpdatePasswordByLinkRepo extends JpaRepository<UpdatePasswordByLink,Long> {
    UpdatePasswordByLink findByEmail(String email);

}
