package com.sanutem.backend.model;

import javax.persistence.Entity;
import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "Doctors")
public class Doctors extends Professionals{

    public Doctors() {
    }
    /*
    public Doctors(Integer id, String dni, String firstName, String lastName, String username, String email, String sex, LocalDate birthday, String home_address, String password, Instant created, boolean enabled) {
        super(id, dni, firstName, lastName, username, email, sex, birthday, home_address, password, created, enabled);
    }

    public Doctors(Integer id, String dni, String firstName, String lastName, String username, String email, String sex, LocalDate birthday, String home_address, Long licenseNumber, String specialization, String password, Instant created, boolean enabled) {
        super(id, dni, firstName, lastName, username, email, sex, birthday, home_address, licenseNumber, specialization, password, created, enabled);
    }*/
}
