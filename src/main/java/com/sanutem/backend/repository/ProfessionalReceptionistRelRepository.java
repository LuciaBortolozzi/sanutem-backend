package com.sanutem.backend.repository;

import com.sanutem.backend.model.ProfessionalReceptionistRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProfessionalReceptionistRelRepository extends JpaRepository<ProfessionalReceptionistRel, Integer> {

    @Query("SELECT p.idProfRecep FROM ProfessionalReceptionistRel p WHERE p.idProfessional = ?1 AND p.idReceptionist = ?2 ")
    Integer findIDByIDProfessionalAndIDReceptionist(Integer idProfessional, Integer idReceptionist);

    @Query("SELECT p.idProfessional FROM ProfessionalReceptionistRel p WHERE p.idReceptionist = ?1 ")
    Integer findIDProfessionalByIDReceptionist(Integer idReceptionist);

    @Query("SELECT u.username FROM ProfessionalReceptionistRel prr JOIN Users as u ON prr.idProfessional = u.id WHERE prr.idReceptionist = ?1")
    String findUsernameProfessionalByIDReceptionist(Integer idReceptionist);
}
