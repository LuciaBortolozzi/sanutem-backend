package com.sanutem.backend.repository;

import com.sanutem.backend.model.MedicalTests;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalTestsRepository extends JpaRepository<MedicalTests, Integer> {
}
