package com.sanutem.backend.repository;

import com.sanutem.backend.model.ProfessionalPatientRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfessionalPatientRelRepository extends JpaRepository<ProfessionalPatientRel, Long> {

    @Query("SELECT p.idPatient FROM ProfessionalPatientRel p WHERE p.idProfessional = ?1")
    String[] findIDPatientByIDProfessional(Integer idProfessional);
}
