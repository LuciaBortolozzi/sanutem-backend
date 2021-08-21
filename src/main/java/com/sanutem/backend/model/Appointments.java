package com.sanutem.backend.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Appointments")
@Table(
        name = "appointments",
        uniqueConstraints ={
                @UniqueConstraint(name = "appointments_date", columnNames = {"date", "hour"})
        }
)
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
            name = "date",
            nullable = false
    )
    private Date date;
    @Column(
            name = "hour",
            nullable = false
    )
    private String hour;

    public Appointments() {}

    public Appointments(Integer idAppointments, Date date, String hour) {
        this.idAppointments = idAppointments;
        this.date = date;
        this.hour = hour;
    }

    public Integer getIdAppointments() {
        return idAppointments;
    }

    public void setIdAppointments(Integer idAppointments) {
        this.idAppointments = idAppointments;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
