package com.sanutem.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dni;
    private String homeAddress;
    private String birthday;
    private String sex;
    private String role;
    private String blood_type;
    private String medical_history;
    private String surgeries;
    private String medicines;
    private String license_number;
    private String specialization;
    private String province;
    private String healthInsurances;
}
