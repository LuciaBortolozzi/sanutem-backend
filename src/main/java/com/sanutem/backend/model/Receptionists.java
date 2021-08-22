package com.sanutem.backend.model;

import javax.persistence.Entity;
import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "Receptionists")
public class Receptionists extends Users {

    public Receptionists() {
    }

    public Receptionists(Integer id, String dni, String firstName, String lastName, String username, String email, String sex, LocalDate birthday, String home_address, String password, Instant created, boolean enabled) {
        super(id, dni, firstName, lastName, username, email, sex, birthday, home_address, password, created, enabled);
    }
}
