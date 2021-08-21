package com.sanutem.backend.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "Doctors")
public class Doctors extends Professionals{

    public Doctors() {
    }

    public Doctors(Integer id, String dni, String firstName, String lastName, String userName, String email, String sex, LocalDate birthday, String address) {
        super(id, dni, firstName, lastName, userName, email, sex, birthday, address);
    }

    public Doctors(Integer id, String dni, String firstName, String lastName, String userName, String email, String sex, LocalDate birthday, String address, Long licenseNumber, String specialization) {
        super(id, dni, firstName, lastName, userName, email, sex, birthday, address, licenseNumber, specialization);
    }
}
