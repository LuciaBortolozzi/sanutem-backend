package com.sanutem.backend.repository;

import com.sanutem.backend.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointments, Integer> {
}
