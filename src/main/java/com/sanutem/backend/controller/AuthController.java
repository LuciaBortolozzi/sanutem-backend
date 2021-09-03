package com.sanutem.backend.controller;

import com.sanutem.backend.dto.AuthenticationResponse;
import com.sanutem.backend.dto.LoginRequest;
import com.sanutem.backend.dto.RefreshTokenRequest;
import com.sanutem.backend.dto.RegisterRequest;
import com.sanutem.backend.model.Users;
import com.sanutem.backend.repository.UsersRepository;
import com.sanutem.backend.service.AuthService;
import com.sanutem.backend.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final UsersRepository usersRepository;

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

    @GetMapping("/user-profile/{username}/") //VER ESTO DE ACA
    public Optional<Users> getUserDetails(@PathVariable String username){
        System.out.println("hola manola");
        System.out.println("username:" + username + "query:" +usersRepository.findByUsername(username).get().getEmail());
        return usersRepository.findByUsername(username);
    }
}