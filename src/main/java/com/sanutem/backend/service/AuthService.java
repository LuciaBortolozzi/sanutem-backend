package com.sanutem.backend.service;

import com.sanutem.backend.dto.*;
import com.sanutem.backend.enums.monthsEnum;
import com.sanutem.backend.enums.daysEnum;
import com.sanutem.backend.enums.timeRangeEnum;
import com.sanutem.backend.exception.AppException;
import com.sanutem.backend.model.*;
import com.sanutem.backend.repository.*;
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

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.*;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final GeneralParameterRepository generalParameterRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository userRepository;
    private final PetsRepository petsRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;
    private final ProfessionalReceptionistRelRepository professionalReceptionistRelRepository;
    private final ProfessionalAvailabilityRepository professionalAvailabilityRepository;
    private final AppointmentsRepository appointmentsRepository;
    private final MedicalHistoryRepository medicalHistoryRepository;

    public void signup(RegisterRequest registerRequest) {
        Users user = new Users();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setDni(registerRequest.getDni());
        user.setHomeAddress(registerRequest.getHomeAddress());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate  birthday = LocalDate.parse(registerRequest.getBirthday(), df);

        user.setBirthday(birthday);
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

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate  birthday = LocalDate.parse(registerPetRequest.getBirthday(), df);

        pet.setBirthday(birthday);
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

    public void availability(AvailabilityRequest availabilityRequest) throws ParseException {
        ProfessionalAvailability professionalAvailability = new ProfessionalAvailability();
        ProfessionalAvailability professionalAvailabilityAux = new ProfessionalAvailability();

        professionalAvailability.setMonth(availabilityRequest.getMonth());

        String days = new String();
        days = daysSelection(availabilityRequest);
        professionalAvailability.setWeekDays(days);

        String hours = new String();
        hours = rageTimeSelection(availabilityRequest);
        professionalAvailability.setTimeRange(hours);

        Integer idReceptionist = userRepository.findIDByUsername(availabilityRequest.getNameReceptionist());
        Integer idProfessional = professionalReceptionistRelRepository.findIDProfessionalByIDReceptionist(idReceptionist);
        String userNameProfessional = userRepository.findUsernameByID(idProfessional);
        professionalAvailability.setUserNameProfessional(userNameProfessional);

        professionalAvailabilityAux = professionalAvailabilityRepository.getProfessionalAvailabilityByMonthAndUserNameProfessional(availabilityRequest.getMonth(), userNameProfessional);
        if(professionalAvailabilityAux==null){

            createNewAppointments(professionalAvailability);
            professionalAvailabilityRepository.save(professionalAvailability);
        }
    }

    public void createNewAppointments(ProfessionalAvailability professionalAvailability) throws ParseException {

        String[] timeRange = professionalAvailability.getTimeRange().split(";");
        String[] rageTimeToHours = rageTimeToHours(timeRange);
        Integer months = monthsEnum.MONTH.findMonthValueByMonthName(professionalAvailability.getMonth());
        Integer monthsDays = numberOfDaysInMonth(months);

        Date currentYear=new Date();

        for(int i=1;i<=monthsDays;i++){

            Calendar calendar = Calendar.getInstance();
            String input_date= i + "/" + months + "/" + calendar.get(Calendar.YEAR);
            SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
            Date dt1=format1.parse(input_date);
            Calendar c = Calendar.getInstance();
            c.setTime(dt1);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

            String dayOfWeekName = daysEnum.DAY.findDayNameByDayValue(dayOfWeek);

            if(professionalAvailability.getWeekDays().contains(dayOfWeekName)){

                for(String RT:rageTimeToHours){

                    if(RT!=null){
                        Appointments appointments = new Appointments();
                        appointments.setFreeAppointment(true);
                        appointments.setDate(input_date);
                        appointments.setHour(RT);
                        appointments.setUserNameProfessional(professionalAvailability.getUserNameProfessional());
                        appointmentsRepository.save(appointments);
                    }
                }
            }
        }
    }

    public static int numberOfDaysInMonth(int month){

        int daysNumber=-1;

        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysNumber=31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysNumber=30;
                break;
            case 2:

                Date currentYear=new Date();
                if(isLeapYear(1900 + currentYear.getYear())){
                    daysNumber=29;
                }else{
                    daysNumber=28;
                }
                break;

        }

        return daysNumber;
    }

    public static boolean isLeapYear(int anio) {

        GregorianCalendar calendar = new GregorianCalendar();
        boolean isLeapYear = false;
        if (calendar.isLeapYear(anio)) {
            isLeapYear = true;
        }
        return isLeapYear;

    }

    public String[] rageTimeToHours(String[] timeRange){

        String[] rageTimeToHours = new String[100];
        Integer appointmentDuration = generalParameterRepository.getDurationValueByDurationDescription("appointmentDuration");
        Integer cont = 1;

        for(String TR :timeRange){
            Integer minutes = 0;
            String rageValue = timeRangeEnum.RANGETIME.findTimeRangeValueByTimeRangeName(TR);
            String[] trsep = rageValue.split("-");

            while(minutes<60){

                rageTimeToHours[cont] = (String.format("%02d", Integer.parseInt(trsep[0])) + ":" + String.format("%02d",minutes));
                cont = cont+1;
                minutes = minutes + appointmentDuration;
            }
        }
        return rageTimeToHours;
    }

    public String daysSelection(AvailabilityRequest availabilityRequest){

        String days = new String();
        if(availabilityRequest.getDays()[0].equals("true")){

            days = days + "Monday;";
        }
        if(availabilityRequest.getDays()[1].equals("true")){

            days = days + "Tuesday;";
        }
        if(availabilityRequest.getDays()[2].equals("true")){

            days = days + "Wednesday;";
        }
        if(availabilityRequest.getDays()[3].equals("true")){

            days = days + "Thursday;";
        }
        if(availabilityRequest.getDays()[4].equals("true")){

            days = days + "Friday;";
        }
        if(availabilityRequest.getDays()[5].equals("true")){

            days = days + "Saturday;";
        }
        return days;
    }

    public String rageTimeSelection(AvailabilityRequest availabilityRequest){

        String hours = new String();
        if(availabilityRequest.getHours()[0].equals("true")){

            hours = hours + "range_1;";
        }
        if(availabilityRequest.getHours()[1].equals("true")){

            hours = hours + "range_2;";
        }
        if(availabilityRequest.getHours()[2].equals("true")){

            hours = hours + "range_3;";
        }
        if(availabilityRequest.getHours()[3].equals("true")){

            hours = hours + "range_4;";
        }
        if(availabilityRequest.getHours()[4].equals("true")){

            hours = hours + "range_5;";
        }
        if(availabilityRequest.getHours()[5].equals("true")){

            hours = hours + "range_6;";
        }
        if(availabilityRequest.getHours()[6].equals("true")){

            hours = hours + "range_7;";
        }
        if(availabilityRequest.getHours()[7].equals("true")){

            hours = hours + "range_8;";
        }
        if(availabilityRequest.getHours()[8].equals("true")){

            hours = hours + "range_9;";
        }
        if(availabilityRequest.getHours()[9].equals("true")){

            hours = hours + "range_10;";
        }
        if(availabilityRequest.getHours()[10].equals("true")){

            hours = hours + "range_11;";
        }
        if(availabilityRequest.getHours()[11].equals("true")){

            hours = hours + "range_12;";
        }
        if(availabilityRequest.getHours()[12].equals("true")){

            hours = hours + "range_13;";
        }
        return hours;
    }

    public void medicalHistory(AddMedicalHistoryRequest addMedicalHistoryRequest) {

        Integer patientID = userRepository.findIDByUsername(addMedicalHistoryRequest.getPatientName());
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setDetails(addMedicalHistoryRequest.getDetails());
        //medicalHistory.setDate(addMedicalHistoryRequest.getDate());
        medicalHistory.setId(patientID);

        medicalHistoryRepository.save(medicalHistory);

    }
}
