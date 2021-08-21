package com.sanutem.backend.repository;

import com.sanutem.backend.model.Patients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<Patients, Integer> {
}
