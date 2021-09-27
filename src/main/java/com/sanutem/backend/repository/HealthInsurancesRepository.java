package com.sanutem.backend.repository;

import com.sanutem.backend.model.HealthInsurances;
import com.sanutem.backend.model.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HealthInsurancesRepository extends JpaRepository<HealthInsurances, Long> {

    @Query("SELECT h.healthInsurancesName FROM HealthInsurances h")
    String[] findAllHealthInsurances();
}
