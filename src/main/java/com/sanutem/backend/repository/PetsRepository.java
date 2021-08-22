package com.sanutem.backend.repository;

import com.sanutem.backend.model.Pets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetsRepository extends JpaRepository<Pets, Integer> {
}
