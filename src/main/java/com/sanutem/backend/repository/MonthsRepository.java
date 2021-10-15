package com.sanutem.backend.repository;

import com.sanutem.backend.model.HealthInsurances;
import com.sanutem.backend.model.Months;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MonthsRepository extends JpaRepository<Months, Long> {

    @Query("SELECT m.nameMonth FROM Months m")
    String[] findAllMonths();
}
