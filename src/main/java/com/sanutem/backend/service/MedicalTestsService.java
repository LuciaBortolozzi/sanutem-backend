package com.sanutem.backend.service;

import com.sanutem.backend.model.MedicalTests;
import com.sanutem.backend.repository.MedicalTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class MedicalTestsService {

    @Autowired
    private MedicalTestsRepository medicalTestsRepository;

    public MedicalTests store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        MedicalTests MedicalTests = new MedicalTests(fileName, file.getContentType(), file.getBytes());

        return medicalTestsRepository.save(MedicalTests);
    }

    public MedicalTests getFile(String id) {
        return medicalTestsRepository.findById(id).get();
    }

    public Stream<MedicalTests> getAllFiles() {
        return medicalTestsRepository.findAll().stream();
    }
}
