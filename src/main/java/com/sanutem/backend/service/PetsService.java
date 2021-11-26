package com.sanutem.backend.service;

import com.sanutem.backend.dto.RegisterPetRequest;
import com.sanutem.backend.dto.UpdatePetRequest;
import com.sanutem.backend.model.Pets;
import com.sanutem.backend.repository.PetsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
@Transactional
public class PetsService {
    private final PetsRepository petsRepository;

    public void registerPet(RegisterPetRequest registerPetRequest) {
        Pets pet = new Pets();
        pet.setName(registerPetRequest.getName());
        pet.setSpecies(registerPetRequest.getSpecies());
        pet.setBreed(registerPetRequest.getBreed());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse(registerPetRequest.getBirthday(), df);

        pet.setBirthday(birthday);
        pet.setSex(registerPetRequest.getSex());
        pet.setMedicalHistory(registerPetRequest.getMedical_history());
        pet.setSurgeries(registerPetRequest.getSurgeries());
        pet.setMedicines(registerPetRequest.getMedicines());
        pet.setNameUser(registerPetRequest.getNameUser());

        petsRepository.save(pet);
    }

    public void updatePet(UpdatePetRequest updatePetRequest) {
        petsRepository.updatePetByUsernameAndIDPet(updatePetRequest.getNameUser(), updatePetRequest.getPet(),
                updatePetRequest.getSpecies(), updatePetRequest.getBreed(), updatePetRequest.getSex(),
                updatePetRequest.getMedicalHistory(), updatePetRequest.getSurgeries(), updatePetRequest.getMedicines(), Integer.parseInt(updatePetRequest.getIdPet()));
    }
}
