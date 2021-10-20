package com.sanutem.backend.repository;

import com.sanutem.backend.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AppointmentsRepository extends JpaRepository<Appointments, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Appointments a SET a.userNamePatient = ?1, a.freeAppointment = 0 WHERE a.idAppointments = ?2")
    void scheduleAppointmentById(String userName, Integer id);

    @Query("SELECT a FROM Appointments a WHERE a.userNameProfessional = ?1")
    List<Appointments> getAppointmentsByUsername(String userNameProfessional);
}
