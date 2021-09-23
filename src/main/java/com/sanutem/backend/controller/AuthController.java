package com.sanutem.backend.controller;

import com.sanutem.backend.dto.*;
import com.sanutem.backend.model.Pets;
import com.sanutem.backend.model.Users;
import com.sanutem.backend.repository.PetsRepository;
import com.sanutem.backend.repository.UsersRepository;
import com.sanutem.backend.repository.VerificationTokenRepository;
import com.sanutem.backend.service.AuthService;
import com.sanutem.backend.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        Optional<Users> user = usersRepository.findByUsername(username);
        System.out.println(user.get().getHomeAddress());
        return usersRepository.findByUsername(username);
    }

    @GetMapping("/user-profile/{username}/pets")
    public List<Pets> getPets(@PathVariable String username) {
        return petsRepository.getPetsByUsername(username);
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
}
