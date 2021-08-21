package com.sanutem.backend.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity(name = "Receptionists")
public class Receptionists extends Users {

    public Receptionists() {
    }

    public Receptionists(Integer id, String dni, String firstName, String lastName, String userName, String email, String sex, LocalDate birthday, String address) {
        super(id, dni, firstName, lastName, userName, email, sex, birthday, address);
    }
}
