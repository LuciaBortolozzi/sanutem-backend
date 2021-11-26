package com.sanutem.backend.controller;

import com.sanutem.backend.dto.*;
import com.sanutem.backend.model.*;
import com.sanutem.backend.repository.*;
import com.sanutem.backend.service.AuthService;
import com.sanutem.backend.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final UsersRepository usersRepository;
    private final ProvincesRepository provincesRepository;
    private final SpecializationsRepository specializationsRepository;
    private final HealthInsurancesRepository healthInsurancesRepository;
    private final MonthsRepository monthsRepository;
    private final ProfessionalPatientRelRepository professionalPatientRelRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

    @GetMapping("/user-profile/{username}/")
    public Optional<Users> getUserDetails(@PathVariable String username) {
        return usersRepository.findByUsername(username);
    }

    @DeleteMapping("/settings/{username}/")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        Optional<Users> userToDelete = usersRepository.findByUsername(username);
        authService.deleteUser(username, userToDelete);

        if (userToDelete.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user-data/{username}/")
    public Optional<Users> getUserProfileData(@PathVariable String username) {
        return usersRepository.findByUsername(username);
    }

    @PostMapping("/update/")
    public ResponseEntity<String> update(@RequestBody UpdateRequest updateRequest) {
        authService.update(updateRequest);
        return new ResponseEntity<>("User Update Successful",
                OK);
    }

    @PostMapping("/link-receptionist")
    public ResponseEntity<String> linkReceptionist(@RequestBody LinkReceptionistRequest linkReceptionistRequest) {

        if (authService.linkReceptionist(linkReceptionistRequest)) {

            return new ResponseEntity<>("Link Receptionist Successful",
                    OK);
        } else {
            return new ResponseEntity<>("The ID Receptionist does not exist",
                    NOT_FOUND);
        }
    }

    @GetMapping("/provinces-data/")
    public String[] getProvinces() {
        return provincesRepository.findAllProvinces();
    }

    @GetMapping("/specializations-data/")
    public String[] getSpecializations() {
        return specializationsRepository.findAllSpecializations();
    }

    @GetMapping("/healthInsurances-data/")
    public String[] getHealthInsurances() {
        return healthInsurancesRepository.findAllHealthInsurances();
    }

    @GetMapping("/search/{specialization}/{province}/{healthInsurance}/")
    public Users[] search(@PathVariable String specialization, @PathVariable String province, @PathVariable String healthInsurance) {
        return usersRepository.findProfessionalBySpecializationAndProvinceAndHealthInsurance(specialization, province, healthInsurance);
    }

    @GetMapping("/months-data/")
    public String[] getMonths() {
        return monthsRepository.findAllMonths();
    }

    @GetMapping("/patients/{username}/")
    public ArrayList<String> getPatients(@PathVariable String username) {
        Integer professionalID = usersRepository.findIDByUsername(username);
        String[] patientListID = professionalPatientRelRepository.findIDPatientByIDProfessional(professionalID);
        ArrayList<String> patients = new ArrayList<String>();
        for (String pa : patientListID) {
            patients.add(usersRepository.findUsernameByID(Integer.parseInt(pa)));
        }
        return patients;
    }

}
