package com.sanutem.backend.repository;

import com.sanutem.backend.model.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvincesRepository extends JpaRepository<Provinces, Long> {

    @Query("SELECT p.provinceName FROM Provinces p")
    String[] findAllProvinces();
}
