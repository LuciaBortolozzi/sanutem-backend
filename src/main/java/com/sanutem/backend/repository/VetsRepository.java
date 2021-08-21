package com.sanutem.backend.repository;

import com.sanutem.backend.model.Vets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetsRepository extends JpaRepository<Vets, Integer> {
}
