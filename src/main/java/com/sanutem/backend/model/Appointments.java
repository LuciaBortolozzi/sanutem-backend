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
            name="appointments_sequence",
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
    private LocalDate date;
    @Column(
            name = "hour"/*,
            nullable = false*/
    )
    private LocalTime hour;
    @Column(
            name = "freeAppointment"/*,
            nullable = false*/
    )
    private Boolean freeAppointment;
    @Column(
            name = "userNamePatience"/*,
            nullable = false*/
    )
    private String userNamePatience;
    @Column(
            name = "userNameProfessional"/*,
            nullable = false*/
    )
    private String userNameProfessional;

    public Appointments() {}

    public Appointments(Integer idAppointments, LocalDate date, LocalTime hour,
                        Boolean freeAppointment, String userNamePatience,
                        String userNameProfessional) {
        this.idAppointments = idAppointments;
        this.date = date;
        this.hour = hour;
        this.freeAppointment = freeAppointment;
        this.userNamePatience = userNamePatience;
        this.userNameProfessional = userNameProfessional;
    }

    public Integer getIdAppointments() {
        return idAppointments;
    }

    public void setIdAppointments(Integer idAppointments) {
        this.idAppointments = idAppointments;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Boolean getFreeAppointment() {return freeAppointment;}

    public void setFreeAppointment(Boolean freeAppointment) {this.freeAppointment = freeAppointment;}

    public String getUserNamePatience() {return userNamePatience;}

    public void setUserNamePatience(String userNamePatience) {this.userNamePatience = userNamePatience;}

    public String getUserNameProfessional() {return userNameProfessional;}

    public void setUserNameProfessional(String userNameProfessional) {this.userNameProfessional = userNameProfessional;}
}
