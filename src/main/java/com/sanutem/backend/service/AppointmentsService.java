package com.sanutem.backend.service;

import com.sanutem.backend.dto.AvailabilityRequest;
import com.sanutem.backend.enums.daysEnum;
import com.sanutem.backend.enums.monthsEnum;
import com.sanutem.backend.enums.timeRangeEnum;
import com.sanutem.backend.model.Appointments;
import com.sanutem.backend.model.ProfessionalAvailability;
import com.sanutem.backend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
@AllArgsConstructor
@Transactional
public class AppointmentsService {

    private final UsersRepository userRepository;
    private final GeneralParameterRepository generalParameterRepository;
    private final ProfessionalAvailabilityRepository professionalAvailabilityRepository;
    private final ProfessionalReceptionistRelRepository professionalReceptionistRelRepository;
    private final AppointmentsRepository appointmentsRepository;

    public void availability(AvailabilityRequest availabilityRequest) throws ParseException {
        ProfessionalAvailability professionalAvailability = new ProfessionalAvailability();
        ProfessionalAvailability professionalAvailabilityAux = new ProfessionalAvailability();

        professionalAvailability.setMonth(availabilityRequest.getMonth());

        String days;
        days = daysSelection(availabilityRequest);
        professionalAvailability.setWeekDays(days);

        String hours;
        hours = rangeTimeSelection(availabilityRequest);
        professionalAvailability.setTimeRange(hours);

        Integer idReceptionist = userRepository.findIDByUsername(availabilityRequest.getNameReceptionist());
        Integer idProfessional = professionalReceptionistRelRepository.findIDProfessionalByIDReceptionist(idReceptionist);
        String userNameProfessional = userRepository.findUsernameByID(idProfessional);
        professionalAvailability.setUserNameProfessional(userNameProfessional);

        professionalAvailabilityAux = professionalAvailabilityRepository.getProfessionalAvailabilityByMonthAndUserNameProfessional(availabilityRequest.getMonth(), userNameProfessional);
        if (professionalAvailabilityAux == null) {

            createNewAppointments(professionalAvailability);
            professionalAvailabilityRepository.save(professionalAvailability);
        }
    }

    public void createNewAppointments(ProfessionalAvailability professionalAvailability) throws ParseException {

        String[] timeRange = professionalAvailability.getTimeRange().split(";");
        String[] rageTimeToHours = rangeTimeToHours(timeRange);
        Integer months = monthsEnum.MONTH.findMonthValueByMonthName(professionalAvailability.getMonth());
        int monthsDays = numberOfDaysInMonth(months);

        Date currentYear = new Date();

        for (int i = 1; i <= monthsDays; i++) {

            Calendar calendar = Calendar.getInstance();
            String input_date = i + "/" + months + "/" + calendar.get(Calendar.YEAR);
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            Date dt1 = format1.parse(input_date);
            Calendar c = Calendar.getInstance();
            c.setTime(dt1);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

            String dayOfWeekName = daysEnum.DAY.findDayNameByDayValue(dayOfWeek);

            if (professionalAvailability.getWeekDays().contains(dayOfWeekName)) {

                for (String RT : rageTimeToHours) {

                    if (RT != null) {
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

    public static int numberOfDaysInMonth(int month) {

        int daysNumber = -1;

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysNumber = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysNumber = 30;
                break;
            case 2:

                Date currentYear = new Date();
                if (isLeapYear(1900 + currentYear.getYear())) {
                    daysNumber = 29;
                } else {
                    daysNumber = 28;
                }
                break;

        }

        return daysNumber;
    }

    public static boolean isLeapYear(int year) {

        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.isLeapYear(year);

    }

    public String[] rangeTimeToHours(String[] timeRange) {

        String[] rangeTimeToHours = new String[100];
        Integer appointmentDuration = generalParameterRepository.getDurationValueByDurationDescription("appointmentDuration");
        int cont = 1;

        for (String TR : timeRange) {
            int minutes = 0;
            String rageValue = timeRangeEnum.RANGETIME.findTimeRangeValueByTimeRangeName(TR);
            String[] trsep = rageValue.split("-");

            while (minutes < 60) {

                rangeTimeToHours[cont] = (String.format("%02d", Integer.parseInt(trsep[0])) + ":" + String.format("%02d", minutes));
                cont = cont + 1;
                minutes = minutes + appointmentDuration;
            }
        }
        return rangeTimeToHours;
    }

    public String daysSelection(AvailabilityRequest availabilityRequest) {

        String days = "";
        if (availabilityRequest.getDays()[0].equals("true")) {

            days = days + "Monday;";
        }
        if (availabilityRequest.getDays()[1].equals("true")) {

            days = days + "Tuesday;";
        }
        if (availabilityRequest.getDays()[2].equals("true")) {

            days = days + "Wednesday;";
        }
        if (availabilityRequest.getDays()[3].equals("true")) {

            days = days + "Thursday;";
        }
        if (availabilityRequest.getDays()[4].equals("true")) {

            days = days + "Friday;";
        }
        if (availabilityRequest.getDays()[5].equals("true")) {

            days = days + "Saturday;";
        }
        return days;
    }

    public String rangeTimeSelection(AvailabilityRequest availabilityRequest) {

        String hours = "";
        if (availabilityRequest.getHours()[0].equals("true")) {

            hours = hours + "range_1;";
        }
        if (availabilityRequest.getHours()[1].equals("true")) {

            hours = hours + "range_2;";
        }
        if (availabilityRequest.getHours()[2].equals("true")) {

            hours = hours + "range_3;";
        }
        if (availabilityRequest.getHours()[3].equals("true")) {

            hours = hours + "range_4;";
        }
        if (availabilityRequest.getHours()[4].equals("true")) {

            hours = hours + "range_5;";
        }
        if (availabilityRequest.getHours()[5].equals("true")) {

            hours = hours + "range_6;";
        }
        if (availabilityRequest.getHours()[6].equals("true")) {

            hours = hours + "range_7;";
        }
        if (availabilityRequest.getHours()[7].equals("true")) {

            hours = hours + "range_8;";
        }
        if (availabilityRequest.getHours()[8].equals("true")) {

            hours = hours + "range_9;";
        }
        if (availabilityRequest.getHours()[9].equals("true")) {

            hours = hours + "range_10;";
        }
        if (availabilityRequest.getHours()[10].equals("true")) {

            hours = hours + "range_11;";
        }
        if (availabilityRequest.getHours()[11].equals("true")) {

            hours = hours + "range_12;";
        }
        if (availabilityRequest.getHours()[12].equals("true")) {

            hours = hours + "range_13;";
        }
        return hours;
    }
}
