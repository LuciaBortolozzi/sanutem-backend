package com.sanutem.backend.controller;

import com.sanutem.backend.dto.AvailabilityRequest;
import com.sanutem.backend.dto.CancelRequest;
import com.sanutem.backend.dto.ScheduleRequest;
import com.sanutem.backend.model.Appointments;
import com.sanutem.backend.model.ProfessionalPatientRel;
import com.sanutem.backend.repository.AppointmentsRepository;
import com.sanutem.backend.repository.ProfessionalPatientRelRepository;
import com.sanutem.backend.repository.ProfessionalReceptionistRelRepository;
import com.sanutem.backend.repository.UsersRepository;
import com.sanutem.backend.service.AppointmentsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AppointmentsController {

    private final UsersRepository usersRepository;
    private final AppointmentsRepository appointmentsRepository;
    private final ProfessionalReceptionistRelRepository profRecepRelRepository;
    private final ProfessionalPatientRelRepository professionalPatientRelRepository;
    private final AppointmentsService appointmentsService;

    @PostMapping("/availability")
    public ResponseEntity<String> availability(@RequestBody AvailabilityRequest availabilityRequest) throws ParseException {
        appointmentsService.availability(availabilityRequest);
        return new ResponseEntity<>("Availability Registration Successful",
                OK);
    }

    @GetMapping("/user-profile/{username}/search/{professional}/schedule")
    public List<Appointments> getAppointments(@PathVariable String professional) {
        return appointmentsRepository.getAppointmentsByUsername(professional);
    }

    @GetMapping("/user-profile/{professional}/view-calendar")
    public List<Appointments> getScheduledAppointments(@PathVariable String professional) {
        return appointmentsRepository.getScheduledAppointmentsByProfessional(professional);
    }

    @GetMapping("/user-profile/{receptionist}/modify-calendar")
    public List<Appointments> getScheduledAppointmentsR(@PathVariable String receptionist) {
        int idReceptionist = usersRepository.findIDByUsername(receptionist);
        String professional = profRecepRelRepository.findUsernameProfessionalByIDReceptionist(idReceptionist);
        return appointmentsRepository.getScheduledAppointmentsByProfessional(professional);
    }

    @PostMapping("/user-profile/{username}/search/{professional}/schedule/{id}")
    public ResponseEntity<String> scheduleAppointment(@RequestBody ScheduleRequest scheduleRequest) {

        ProfessionalPatientRel professionalPatientRel = new ProfessionalPatientRel();
        Integer idPatient = usersRepository.findIDByUsername(scheduleRequest.getUserNamePatient());
        Integer idProfessional = usersRepository.findIDByUsername(scheduleRequest.getUserNameProfessional());
        professionalPatientRel.setIdPatient(idPatient);
        professionalPatientRel.setIdProfessional(idProfessional);
        professionalPatientRelRepository.save(professionalPatientRel);

        appointmentsRepository.scheduleAppointmentById(scheduleRequest.getUserNamePatient(), scheduleRequest.getIdAppointments());
        return new ResponseEntity<>("Schedule Successful",
                OK);
    }

    @PostMapping("/user-profile/modify-calendar/cancel-appointment/")
    public ResponseEntity<String> cancelAppointment(@RequestBody CancelRequest cancelRequest) {
        appointmentsRepository.cancelAppointmentById(cancelRequest.getIdAppointments());
        return new ResponseEntity<>("Cancel Appointment Successful",
                OK);
    }
}
