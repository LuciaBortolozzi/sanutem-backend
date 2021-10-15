package com.sanutem.backend.repository;

import com.sanutem.backend.model.MedicalTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalTestsRepository extends JpaRepository<MedicalTests, String> {
}
