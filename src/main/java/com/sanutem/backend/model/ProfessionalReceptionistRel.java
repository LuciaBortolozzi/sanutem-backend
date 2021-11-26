package com.sanutem.backend.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "ProfessionalReceptionistRel")
public class ProfessionalReceptionistRel {

    @Id
    @SequenceGenerator(
            name = "prof_recep_sequence",
            sequenceName = "prof_recep_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "prof_recep_sequence"
    )
    @Column(
            name = "idProfRecep",
            updatable = false
    )
    private Integer idProfRecep;
    @Column(
            name = "idProfessional",
            updatable = false
    )
    private Integer idProfessional;
    @Column(
            name = "idReceptionist",
            updatable = false
    )
    private Integer idReceptionist;

    public ProfessionalReceptionistRel(Integer idProfRecep, Integer idProfessional, Integer idReceptionist) {
        this.idProfRecep = idProfRecep;
        this.idProfessional = idProfessional;
        this.idReceptionist = idReceptionist;
    }

    public ProfessionalReceptionistRel() {
    }

    public Integer getIdProfRecep() {
        return idProfRecep;
    }

    public void setIdProfRecep(Integer idProfRecep) {
        this.idProfRecep = idProfRecep;
    }

    public Integer getIdProfessional() {
        return idProfessional;
    }

    public void setIdProfessional(Integer idProfessional) {
        this.idProfessional = idProfessional;
    }

    public Integer getIdReceptionist() {
        return idReceptionist;
    }

    public void setIdReceptionist(Integer idReceptionist) {
        this.idReceptionist = idReceptionist;
    }
}
