package com.sanutem.backend.repository;

import com.sanutem.backend.model.GeneralParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GeneralParameterRepository extends JpaRepository<GeneralParameter, Integer> {

    @Query("SELECT g.valueGeneralParameter FROM GeneralParameter g WHERE g.descriptionGeneralParameter = ?1")
    Integer getDurationValueByDurationDescription(String durationDescription);
}
