package com.sanutem.backend.model;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "MedicalHistory")
public class MedicalHistory {

    @Id
    @SequenceGenerator(
            name = "medical_history_sequence",
            sequenceName = "medical_history_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "medical_history_sequence"
    )
    @Column(
            name = "idMedicalHistory",
            updatable = false
    )
    private Integer idMedicalHistory;
    @Column(
            name = "id"/*,
            nullable = false*/
    )
    private Integer id; // user id
    @Column(
            name = "date"/*,
            nullable = false*/
    )
    private LocalDate date;
    @Column(
            name = "details"/*,
            nullable = false*/
    )
    private String details;

    public MedicalHistory() {
    }

    public MedicalHistory(Integer idMedicalHistory, Integer id, LocalDate date, String details) {
        this.idMedicalHistory = idMedicalHistory;
        this.id = id;
        this.date = date;
        this.details = details;
    }

    public Integer getIdMedicalHistory() {
        return idMedicalHistory;
    }

    public void setIdMedicalHistory(Integer idMedicalHistory) {
        this.idMedicalHistory = idMedicalHistory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
