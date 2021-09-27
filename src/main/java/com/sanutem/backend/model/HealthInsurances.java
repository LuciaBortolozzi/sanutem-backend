package com.sanutem.backend.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "HealthInsurances")
public class HealthInsurances {

    @Id
    @SequenceGenerator(
            name="healthInsurances_sequence",
            sequenceName = "healthInsurances_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "healthInsurances_sequence"
    )
    @Column(
            name = "idHealthInsurances",
            updatable = false
    )
    private Integer idHealthInsurances;
    @Column(
            name = "healthInsurancesName",
            updatable = false
    )
    private String healthInsurancesName;

    public HealthInsurances() {
    }

    public HealthInsurances(Integer idHealthInsurances, String healthInsurancesName) {
        this.idHealthInsurances = idHealthInsurances;
        this.healthInsurancesName = healthInsurancesName;
    }

    public Integer getIdHealthInsurances() {
        return idHealthInsurances;
    }

    public void setIdHealthInsurances(Integer idHealthInsurances) {
        this.idHealthInsurances = idHealthInsurances;
    }

    public String getHealthInsurancesName() {
        return healthInsurancesName;
    }

    public void setHealthInsurancesName(String healthInsurancesName) {
        this.healthInsurancesName = healthInsurancesName;
    }
}
