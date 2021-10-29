package com.sanutem.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelRequest {

    private String username;
    private Integer idAppointments;
    private String date;
    private String hour;
    private Boolean freeAppointment;
    private String userNamePatient;
    private String userNameProfessional;
}
