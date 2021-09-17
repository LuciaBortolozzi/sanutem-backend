package com.sanutem.backend.repository;

import com.sanutem.backend.model.ProfessionalReceptionistRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfessionalReceptionistRelRepository extends JpaRepository<ProfessionalReceptionistRel, Integer> {

    @Query("SELECT p.idProfRecep FROM ProfessionalReceptionistRel p WHERE p.idProfessional = ?1 AND p.idReceptionist = ?2 ")
    Integer findIDByIDProfessionalAndIDReceptionist(Integer idProfessional, Integer idReceptionist);
}
