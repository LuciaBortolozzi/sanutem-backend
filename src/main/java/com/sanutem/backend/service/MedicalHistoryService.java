package com.sanutem.backend.service;

import com.sanutem.backend.dto.AddMedicalHistoryRequest;
import com.sanutem.backend.model.MedicalHistory;
import com.sanutem.backend.repository.MedicalHistoryRepository;
import com.sanutem.backend.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
@Transactional
public class MedicalHistoryService {

    private final UsersRepository userRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;

    public void medicalHistory(AddMedicalHistoryRequest addMedicalHistoryRequest) {

        Integer patientID = userRepository.findIDByUsername(addMedicalHistoryRequest.getPatientName());
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setDetails(addMedicalHistoryRequest.getDetails());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(addMedicalHistoryRequest.getDate(), df);

        medicalHistory.setDate(date);
        medicalHistory.setId(patientID);

        medicalHistoryRepository.save(medicalHistory);

    }
}
