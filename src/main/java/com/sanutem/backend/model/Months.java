package com.sanutem.backend.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Months")
public class Months {

    @Id
    @SequenceGenerator(
            name = "months_sequence",
            sequenceName = "months_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "months_sequence"
    )
    @Column(
            name = "idMonth",
            updatable = false
    )
    private Integer idMonth;

    @Column(
            name = "numberMonth",
            updatable = false
    )
    private Integer numberMonth;

    @Column(
            name = "nameMonth",
            updatable = false
    )
    private String nameMonth;

    public Months() {
    }

    public Months(Integer idMonth, Integer numberMonth, String nameMonth) {
        this.idMonth = idMonth;
        this.numberMonth = numberMonth;
        this.nameMonth = nameMonth;
    }

    public Integer getIdMonth() {
        return idMonth;
    }

    public void setIdMonth(Integer idMonth) {
        this.idMonth = idMonth;
    }

    public Integer getNumberMonth() {
        return numberMonth;
    }

    public void setNumberMonth(Integer numberMonth) {
        this.numberMonth = numberMonth;
    }

    public String getNameMonth() {
        return nameMonth;
    }

    public void setNameMonth(String nameMonth) {
        this.nameMonth = nameMonth;
    }
}
