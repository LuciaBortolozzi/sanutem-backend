package com.sanutem.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePetRequest {

    private String nameUser;
    private String idPet;
    private String pet;
    private String species;
    private String breed;
    // private String birthday;
    private String sex;
    private String medicalHistory;
    private String surgeries;
    private String medicines;
}
