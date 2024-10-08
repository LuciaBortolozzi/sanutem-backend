package com.sanutem.backend.controller;

import com.sanutem.backend.message.ResponseFile;
import com.sanutem.backend.message.ResponseMessage;
import com.sanutem.backend.model.MedicalTests;
import com.sanutem.backend.service.MedicalTestsService;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class MedicalTestsController {
    @Autowired
    private MedicalTestsService medicalTestService;

    @PostMapping("/user-profile/{username}/medical-tests/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@PathVariable String username, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            medicalTestService.store(username, file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/user-profile/{username}/medical-tests/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = medicalTestService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/user-profile/{username}/medical-tests/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        MedicalTests medicalTest = medicalTestService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + medicalTest.getName() + "\"")
                .body(medicalTest.getData());
    }
}
