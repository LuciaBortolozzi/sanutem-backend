package com.sanutem.backend.service;

import com.sanutem.backend.dto.*;
import com.sanutem.backend.exception.AppException;
import com.sanutem.backend.model.*;
import com.sanutem.backend.repository.PetsRepository;
import com.sanutem.backend.repository.ProfessionalReceptionistRelRepository;
import com.sanutem.backend.repository.UsersRepository;
import com.sanutem.backend.repository.VerificationTokenRepository;
import com.sanutem.backend.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository userRepository;
    private final PetsRepository petsRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final ProfessionalReceptionistRelRepository professionalReceptionistRelRepository;

    public void signup(RegisterRequest registerRequest) {
        Users user = new Users();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setDni(registerRequest.getDni());
        user.setHomeAddress(registerRequest.getAddress());
        //user.setBirthday(LocalDate.parse(registerRequest.getBirthday()));
        user.setSex(registerRequest.getSex());
        user.setCreated(Instant.now());
        user.setEnabled(false);
        user.setRole(registerRequest.getRole());
        user.setBlood_type(registerRequest.getBlood_type());
        user.setMedical_history(registerRequest.getMedical_history());
        user.setSurgeries(registerRequest.getSurgeries());
        user.setMedicines(registerRequest.getMedicines());
        user.setLicense_number(registerRequest.getLicense_number());
        user.setSpecialization(registerRequest.getSpecialization());
        user.setProvince(registerRequest.getProvince());
        user.setHealthInsurances(registerRequest.getHealthInsurances());

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Por favor active su cuenta",
                user.getEmail(), "Gracias por registrarse en Sanutem, " +
                "por favor active su cuenta haciendo click en el siguiente link : " +
                "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    public void registerPet(RegisterPetRequest registerPetRequest) {
        Pets pet = new Pets();
        pet.setName(registerPetRequest.getName());
        pet.setSpecies(registerPetRequest.getSpecies());
        pet.setBreed(registerPetRequest.getBreed());
        //pet.setBirthday(LocalDate.parse(registerPetRequest.getBirthday()));
        pet.setSex(registerPetRequest.getSex());
        pet.setMedicalHistory(registerPetRequest.getMedical_history());
        pet.setSurgeries(registerPetRequest.getSurgeries());
        pet.setMedicines(registerPetRequest.getMedicines());
        pet.setNameUser(registerPetRequest.getNameUser());

        petsRepository.save(pet);
    }

    public Boolean linkReceptionist(LinkReceptionistRequest linkReceptionistRequest) {

        ProfessionalReceptionistRel professionalReceptionistRel = new ProfessionalReceptionistRel();
        Boolean receptionistExist = false;

        if(userRepository.findUsernameByID(Integer.parseInt(linkReceptionistRequest.getIdReceptionist()))!=null){

            Integer idProfessional = userRepository.findIDByUsername(linkReceptionistRequest.getNameProf());

            professionalReceptionistRel.setIdReceptionist(Integer.parseInt(linkReceptionistRequest.getIdReceptionist()));
            professionalReceptionistRel.setIdProfessional(idProfessional);

            if(professionalReceptionistRelRepository.findIDByIDProfessionalAndIDReceptionist(idProfessional,
                    Integer.parseInt(linkReceptionistRequest.getIdReceptionist()) )==null){

                professionalReceptionistRelRepository.save(professionalReceptionistRel);
            }

            receptionistExist = true;
        }

        return receptionistExist;
    }

    public void deleteUser(String username, Optional<Users> userToDelete ) {

        if(userToDelete!=null){
            List<Integer> idPets = petsRepository.findIdPetByUsername(username);

            Integer idToken = verificationTokenRepository.findIdTokenByUsername(username);

            if(idToken!=null){
                verificationTokenRepository.deleteTokenByIdToken(Long.valueOf(idToken));
            }
            if(idPets.size()>0){
                for(Integer id:idPets){
                    petsRepository.deletePetByIdPet(id);
                }
            }
        }
        userRepository.deleteUserById(userToDelete.get().getId());
    }

    @Transactional(readOnly = true)
    public Users getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        Users user = userRepository.findByUsername(username).orElseThrow(() -> new AppException("User not found with name - " + username));
        user.setEnabled(true);
        userRepository.save(user);
    }

    private String generateVerificationToken(Users user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new AppException("Invalid Token")));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public void update(UpdateRequest updateRequest) {
        userRepository.updateUserByUsername(updateRequest.getEmail(),updateRequest.getFirstName(),updateRequest.getLastName(), updateRequest.getSex(), updateRequest.getUsername());
    }
}
