package com.sanutem.backend.repository;

import com.sanutem.backend.model.Pets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public interface PetsRepository extends JpaRepository<Pets, Integer> {

    @Query("SELECT p.idPet FROM Pets p WHERE p.nameUser = ?1")
    List<Integer> findIdPetByUsername(String nameUser);

    @Query("SELECT p FROM Pets p WHERE p.nameUser = ?1")
    List<Pets> getPetsByUsername(String nameUser);

    @Transactional
    @Modifying
    @Query("DELETE FROM Pets p WHERE p.idPet = ?1")
    void deletePetByIdPet(Integer idPet);

    @Query("SELECT p FROM Pets p WHERE p.idPet = ?1")
    Pets getPetByID(Integer idPet);

    @Modifying
    @Query("UPDATE Pets p SET p.name = ?2, p.species = ?3, p.breed = ?4, p.sex = ?5, p.medicalHistory = ?6, p.surgeries = ?7, p.medicines = ?8 WHERE p.nameUser = ?1 AND p.idPet = ?9")
    void updatePetByUsernameAndIDPet(String nameUser, String pet, String species, String breed, String sex,
                                     String medHistory, String surgeries, String medicines, Integer idPet);


}
