package com.sanutem.backend.repository;

import com.sanutem.backend.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentsRepository extends JpaRepository<Appointments, Integer> {

    @Query("SELECT a.idAppointments FROM Appointments a WHERE a.userNameProfessional = ?1")
    List<Integer> findIdAppointmentByUsername(String userNameProfessional);

    @Query("SELECT a FROM Appointments a WHERE a.userNameProfessional = ?1")
    List<Appointments> getAppointmentsByUsername(String userNameProfessional);
}
