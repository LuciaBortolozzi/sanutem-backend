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

import java.text.ParseException;
import java.util.List;
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
    private final PetsRepository petsRepository;
    private final AppointmentsRepository appointmentsRepository;
    private final ProvincesRepository provincesRepository;
    private final SpecializationsRepository specializationsRepository;
    private final HealthInsurancesRepository healthInsurancesRepository;
    private final MonthsRepository monthsRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @PostMapping("/registerPet")
    public ResponseEntity<String> registerPet(@RequestBody RegisterPetRequest registerPetRequest) {
        authService.registerPet(registerPetRequest);
        return new ResponseEntity<>("Pet Registration Successful",
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

        if (userToDelete != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user-data/{username}/")
    public Optional<Users> getUserProfileData(@PathVariable String username) {
        return usersRepository.findByUsername(username);
    }

    @GetMapping("/user-profile/{username}/pets")
    public List<Pets> getPets(@PathVariable String username) {
        return petsRepository.getPetsByUsername(username);
    }

    @GetMapping("/user-profile/{username}/search/{professional}/schedule")
    public List<Appointments> getAppointments(@PathVariable String professional) {
        return appointmentsRepository.getAppointmentsByUsername(professional);
    }

    @PostMapping("/user-profile/{username}/search/{professional}/schedule/{id}")
    public ResponseEntity<String> scheduleAppointment(@RequestBody ScheduleRequest scheduleRequest) {
        appointmentsRepository.scheduleAppointmentById(scheduleRequest.getUserNamePatient(), scheduleRequest.getIdAppointments());
        return new ResponseEntity<>("Schedule Successful",
                OK);
    }

    @PostMapping("/update/")
    public ResponseEntity<String> update(@RequestBody UpdateRequest updateRequest) {
        authService.update(updateRequest);
        return new ResponseEntity<>("User Update Successful",
                OK);
    }

    @PostMapping("/link-receptionist")
    public ResponseEntity<String> linkReceptionist(@RequestBody LinkReceptionistRequest linkReceptionistRequest) {

        if(authService.linkReceptionist(linkReceptionistRequest)){

            return new ResponseEntity<>("Link Receptionist Successful",
                    OK);
        }else {
            return new ResponseEntity<>("The ID Receptionist does not exist",
                    NOT_FOUND);
        }
    }

    @GetMapping("/provinces-data/")
    public String[] getProvinces() {
        String[] provinces = provincesRepository.findAllProvinces();
        return provinces;
    }

    @GetMapping("/specializations-data/")
    public String[] getSpecializations() {
        String[] specializations = specializationsRepository.findAllSpecializations();
        return specializations;
    }

    @GetMapping("/healthInsurances-data/")
    public String[] getHealthInsurances() {
        String[] healthInsurances = healthInsurancesRepository.findAllHealthInsurances();
        return healthInsurances;
    }

    @GetMapping("/search/{specialization}/{province}/{healthInsurance}/")
    public Users[] search(@PathVariable String specialization,@PathVariable String province,@PathVariable String healthInsurance) {
        Users[] users = usersRepository.findProfessionalBySpecializationAndProvinceAndHealthInsurance(specialization, province, healthInsurance);
        return users;
    }

    @GetMapping("/months-data/")
    public String[] getMonths() {
        String[] months = monthsRepository.findAllMonths();
        return months;
    }

    @PostMapping("/availability")
    public ResponseEntity<String> availability(@RequestBody AvailabilityRequest availabilityRequest) throws ParseException {
        authService.availability(availabilityRequest);
        return new ResponseEntity<>("Availability Registration Successful",
                OK);
    }
}
