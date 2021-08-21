package com.sanutem.backend.repository;

import com.sanutem.backend.model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<Doctors, Integer> {
}
