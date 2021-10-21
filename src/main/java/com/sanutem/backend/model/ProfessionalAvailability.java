package com.sanutem.backend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "ProfessionalAvailability")
public class ProfessionalAvailability {

    @Id
    @SequenceGenerator(
            name="professional_availability_sequence",
            sequenceName = "professional_availability_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "professional_availability_sequence"
    )
    @Column(
            name = "idAppointmentsProfAvailability",
            updatable = false
    )
    private Integer idAppointmentsProfAvailability;
    @Column(
            name = "weekDays"/*,
            nullable = false*/
    )
    private String weekDays;
    @Column(
            name = "timeRange"/*,
            nullable = false*/
    )
    private String timeRange;
    @Column(
            name = "month"/*,
            nullable = false*/
    )
    private String month;
    @Column(
            name = "userNameProfessional"/*,
            nullable = false*/
    )
    private String userNameProfessional;

    public ProfessionalAvailability(Integer idAppointmentsProfAvailability,
                                    String weekDays, String timeRange,
                                    String month, String userNameProfessional) {
        this.idAppointmentsProfAvailability = idAppointmentsProfAvailability;
        this.weekDays = weekDays;
        this.timeRange = timeRange;
        this.month = month;
        this.userNameProfessional = userNameProfessional;
    }

    public ProfessionalAvailability() {
    }

    public Integer getIdAppointmentsProfAvailability() {
        return idAppointmentsProfAvailability;
    }

    public void setIdAppointmentsProfAvailability(Integer idAppointmentsProfAvailability) {
        this.idAppointmentsProfAvailability = idAppointmentsProfAvailability;
    }

    public String getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(String weekDays) {
        this.weekDays = weekDays;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getUserNameProfessional() {
        return userNameProfessional;
    }

    public void setUserNameProfessional(String userNameProfessional) {
        this.userNameProfessional = userNameProfessional;
    }
}
