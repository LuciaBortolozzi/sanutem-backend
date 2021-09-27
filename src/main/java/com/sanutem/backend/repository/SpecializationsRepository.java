package com.sanutem.backend.repository;

import com.sanutem.backend.model.HealthInsurances;
import com.sanutem.backend.model.Specializations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecializationsRepository extends JpaRepository<Specializations, Long> {

    @Query("SELECT s.specializationName FROM Specializations s")
    String[] findAllSpecializations();
}
