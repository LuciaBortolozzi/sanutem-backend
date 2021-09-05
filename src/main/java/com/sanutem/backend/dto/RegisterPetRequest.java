package com.sanutem.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPetRequest {
    private String name;
    private String species;
    private String breed;
    //private String birthday;
    private String sex;
    private String nameUser;
}
