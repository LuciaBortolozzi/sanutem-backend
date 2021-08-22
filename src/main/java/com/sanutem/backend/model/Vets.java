package com.sanutem.backend.model;

import javax.persistence.Entity;
import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "Vets")
public class Vets extends Professionals{

    public Vets() {
    }

    public Vets(Integer id, String dni, String firstName, String lastName, String username, String email, String sex, LocalDate birthday, String home_address, String password, Instant created, boolean enabled) {
        super(id, dni, firstName, lastName, username, email, sex, birthday, home_address, password, created, enabled);
    }

    public Vets(Integer id, String dni, String firstName, String lastName, String username, String email, String sex, LocalDate birthday, String home_address, Long licenseNumber, String specialization, String password, Instant created, boolean enabled) {
        super(id, dni, firstName, lastName, username, email, sex, birthday, home_address, licenseNumber, specialization, password, created, enabled);
    }
}
