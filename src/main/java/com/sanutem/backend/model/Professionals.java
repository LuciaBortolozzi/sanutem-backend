package com.sanutem.backend.model;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "Professionals")
@Table(
        name = "professionals",
        uniqueConstraints ={
                @UniqueConstraint(name = "license_number", columnNames = "licenseNumber")
        }
)
public class Professionals extends Users{

    @Column(
            name = "licenseNumber",
            nullable = false
    )
    private Long licenseNumber;
    @Column(
            name = "specialization",
            nullable = false
    )
    private String specialization;

    public Professionals(){}

    public Professionals(Integer id, String dni, String firstName, String lastName, String userName, String email, String sex, LocalDate birthday, String address, String password, Instant created, boolean enabled){
        super(id, dni, firstName, lastName, userName, email, sex, birthday, address, password, created, enabled);
    }

    public Professionals(Integer id, String dni, String firstName, String lastName, String userName, String email, String sex, LocalDate birthday, String address, Long licenseNumber, String specialization, String password, Instant created, boolean enabled) {
        super(id, dni, firstName, lastName, userName, email, sex, birthday, address, password, created, enabled);
        this.licenseNumber = licenseNumber;
        this.specialization = specialization;
    }

    public Long getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
