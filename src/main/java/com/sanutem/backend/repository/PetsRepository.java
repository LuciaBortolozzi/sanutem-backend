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

    @Transactional
    @Modifying
    @Query("DELETE FROM Pets p WHERE p.idPet = ?1")
    void deletePetByIdPet(Integer idPet);
}
