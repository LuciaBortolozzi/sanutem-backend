package com.sanutem.backend.repository;

import com.sanutem.backend.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {

    @Query("SELECT m FROM MedicalHistory m WHERE m.id = ?1")
    MedicalHistory[] findByIDPatient(Integer patientID);
}
