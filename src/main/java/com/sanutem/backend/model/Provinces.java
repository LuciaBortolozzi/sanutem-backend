package com.sanutem.backend.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Provinces")
public class Provinces {

    @Id
    @SequenceGenerator(
            name="provinces_sequence",
            sequenceName = "provinces_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "provinces_sequence"
    )
    @Column(
            name = "idProvince",
            updatable = false
    )
    private Integer idProvince;
    @Column(
            name = "provinceName",
            updatable = false
    )
    private String provinceName;

    public Provinces() {
    }

    public Provinces(Integer idProvince, String provinceName) {
        this.idProvince = idProvince;
        this.provinceName = provinceName;
    }

    public Integer getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Integer idProvince) {
        this.idProvince = idProvince;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
