package com.sanutem.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dni;
    private String address;
    private String birthday;
    private String sex;
    private String role;
    private String blood_type;
    private String medical_history;
    private String surgeries;
    private String medicines;
    private String license_number;
    private String specialization;
}

