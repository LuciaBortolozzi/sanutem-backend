package com.sanutem.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "Patients")
public class Patients extends Users{

    @Column(
            name = "bloodType"/*,
            nullable = false*/
    )
    private String bloodType;
    @Column(
            name = "medicalHistory"/*,
            nullable = false*/
    )
    private String medicalHistory;
    @Column(
            name = "surgeries"/*,
            nullable = false*/
    )
    private String surgeries;
    @Column(
            name = "medicines"/*,
            nullable = false*/
    )
    private String medicines;

    public Patients() {}

    public Patients(String bloodType, String medicalHistory, String surgeries, String medicines) {
        this.bloodType = bloodType;
        this.medicalHistory = medicalHistory;
        this.surgeries = surgeries;
        this.medicines = medicines;
    }

    public Patients(Integer id, String dni, String firstName, String lastName, String username, String email, String sex, LocalDate birthday, String home_address, String bloodType, String medicalHistory, String surgeries, String medicines, String password, Instant created, boolean enabled, String role) {
        super(id, dni, firstName, lastName, username, email, sex, birthday, home_address, password, created, enabled,role);
        this.bloodType = bloodType;
        this.medicalHistory = medicalHistory;
        this.surgeries = surgeries;
        this.medicines = medicines;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(String surgeries) {
        this.surgeries = surgeries;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }
}
