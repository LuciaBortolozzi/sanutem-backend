package com.sanutem.backend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Appointments")
/*
@Table(
        name = "appointments",
        uniqueConstraints ={
                @UniqueConstraint(name = "appointments_date", columnNames = {"date", "hour"})
        }
)*/
public class Appointments {

    @Id
    @SequenceGenerator(
            name = "appointments_sequence",
            sequenceName = "appointments_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "appointments_sequence"
    )
    @Column(
            name = "idAppointments",
            updatable = false
    )
    private Integer idAppointments;
    @Column(
            name = "date"/*,
            nullable = false*/
    )
    private String date;
    @Column(
            name = "hour"/*,
            nullable = false*/
    )
    private String hour;
    @Column(
            name = "freeAppointment"/*,
            nullable = false*/
    )
    private Boolean freeAppointment;
    @Column(
            name = "userNamePatient"/*,
            nullable = false*/
    )
    private String userNamePatient;
    @Column(
            name = "userNameProfessional"/*,
            nullable = false*/
    )
    private String userNameProfessional;

    public Appointments() {
    }

    public Appointments(Integer idAppointments, String date, String hour,
                        Boolean freeAppointment, String userNamePatient,
                        String userNameProfessional) {
        this.idAppointments = idAppointments;
        this.date = date;
        this.hour = hour;
        this.freeAppointment = freeAppointment;
        this.userNamePatient = userNamePatient;
        this.userNameProfessional = userNameProfessional;
    }

    public Integer getIdAppointments() {
        return idAppointments;
    }

    public void setIdAppointments(Integer idAppointments) {
        this.idAppointments = idAppointments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Boolean getFreeAppointment() {
        return freeAppointment;
    }

    public void setFreeAppointment(Boolean freeAppointment) {
        this.freeAppointment = freeAppointment;
    }

    public String getUserNamePatient() {
        return userNamePatient;
    }

    public void setUserNamePatient(String userNamePatient) {
        this.userNamePatient = userNamePatient;
    }

    public String getUserNameProfessional() {
        return userNameProfessional;
    }

    public void setUserNameProfessional(String userNameProfessional) {
        this.userNameProfessional = userNameProfessional;
    }
}
