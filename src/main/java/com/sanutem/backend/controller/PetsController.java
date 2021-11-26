package com.sanutem.backend.controller;

import com.sanutem.backend.dto.RegisterPetRequest;
import com.sanutem.backend.dto.UpdatePetRequest;
import com.sanutem.backend.model.Pets;
import com.sanutem.backend.repository.PetsRepository;
import com.sanutem.backend.service.PetsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class PetsController {
    private final PetsService petsService;
    private final PetsRepository petsRepository;

    @PostMapping("/registerPet")
    public ResponseEntity<String> registerPet(@RequestBody RegisterPetRequest registerPetRequest) {
        petsService.registerPet(registerPetRequest);
        return new ResponseEntity<>("Pet Registration Successful",
                OK);
    }

    @GetMapping("/user-profile/{username}/pets")
    public List<Pets> getPets(@PathVariable String username) {
        return petsRepository.getPetsByUsername(username);
    }

    @DeleteMapping("/user-profile/{username}/pets/{idPet}/")
    public ResponseEntity<String> deletePet(@PathVariable String idPet, @PathVariable String username) {

        petsRepository.deletePetByIdPet(Integer.parseInt(idPet));
        return new ResponseEntity<>("Pet Delete Successful",
                OK);
    }

    @GetMapping("/user-profile/{username}/pets/{idPet}")
    public Pets getPet(@PathVariable String username, @PathVariable String idPet) {
        System.out.println(idPet);
        Pets pet = petsRepository.getPetByID(Integer.parseInt(idPet));
        System.out.println(pet.getName());
        return pet;
    }

    @PostMapping("/user-profile/pets/update")
    public ResponseEntity<String> update(@RequestBody UpdatePetRequest updatePetRequest) {
        petsService.updatePet(updatePetRequest);
        return new ResponseEntity<>("Pet Update Successful",
                OK);
    }
}
