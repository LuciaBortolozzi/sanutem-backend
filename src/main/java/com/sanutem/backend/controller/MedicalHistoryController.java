package com.sanutem.backend.controller;

import com.sanutem.backend.dto.AddMedicalHistoryRequest;
import com.sanutem.backend.model.MedicalHistory;
import com.sanutem.backend.repository.MedicalHistoryRepository;
import com.sanutem.backend.repository.UsersRepository;
import com.sanutem.backend.service.MedicalHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class MedicalHistoryController {

    private final UsersRepository usersRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;
    private final MedicalHistoryService medicalHistoryService;

    @GetMapping("/searchPatient/{patientsName}/")
    public MedicalHistory[] search(@PathVariable String patientsName) {

        Integer patientID = usersRepository.findIDByUsername(patientsName);
        return medicalHistoryRepository.findByIDPatient(patientID);
    }

    @PostMapping("/saveMedHistory")
    public ResponseEntity<String> saveMedHistory(@RequestBody AddMedicalHistoryRequest addMedicalHistoryRequest) throws ParseException {
        medicalHistoryService.medicalHistory(addMedicalHistoryRequest);
        return new ResponseEntity<>("Medical History Add Successful",
                OK);
    }
}
