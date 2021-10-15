package com.sanutem.backend.repository;

import com.sanutem.backend.model.Pets;
import com.sanutem.backend.model.ProfessionalAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessionalAvailabilityRepository extends JpaRepository<ProfessionalAvailability, Long> {

    @Query("SELECT p FROM ProfessionalAvailability p WHERE p.month = ?1 AND p.userNameProfessional = ?2")
    ProfessionalAvailability getProfessionalAvailabilityByMonthAndUserNameProfessional(String month,String userNameProfessional);
}
