package com.sanutem.backend.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Specializations")
public class Specializations {

    @Id
    @SequenceGenerator(
            name = "specializations_sequence",
            sequenceName = "specializations_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "specializations_sequence"
    )
    @Column(
            name = "idSpecializations",
            updatable = false
    )
    private Integer idSpecializations;
    @Column(
            name = "specializationName",
            updatable = false
    )
    private String specializationName;

    public Specializations() {
    }

    public Specializations(Integer idSpecializations, String specializationName) {
        this.idSpecializations = idSpecializations;
        this.specializationName = specializationName;
    }

    public Integer getIdSpecializations() {
        return idSpecializations;
    }

    public void setIdSpecializations(Integer idSpecializations) {
        this.idSpecializations = idSpecializations;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }
}
