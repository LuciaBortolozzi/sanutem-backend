package com.sanutem.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
    private int idAppointments;
    private String date;
    private String hour;
    private boolean freeAppointment;
    private String userNamePatient;
    private String userNameProfessional;
}

