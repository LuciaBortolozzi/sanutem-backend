package com.sanutem.backend.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "ProfessionalPatientRel")
public class ProfessionalPatientRel {

    @Id
    @SequenceGenerator(
            name="prof_patient_sequence",
            sequenceName = "prof_patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "prof_patient_sequence"
    )
    @Column(
            name = "idProfPatient",
            updatable = false
    )
    private Integer idProfPatient;
    @Column(
            name = "idProfessional",
            updatable = false
    )
    private Integer idProfessional;
    @Column(
            name = "idPatient",
            updatable = false
    )
    private Integer idPatient;

    public ProfessionalPatientRel() {}

    public ProfessionalPatientRel(Integer idProfPatient, Integer idProfessional, Integer idPatient) {
        this.idProfPatient = idProfPatient;
        this.idProfessional = idProfessional;
        this.idPatient = idPatient;
    }

    public Integer getIdProfPatient() {
        return idProfPatient;
    }

    public void setIdProfPatient(Integer idProfPatient) {
        this.idProfPatient = idProfPatient;
    }

    public Integer getIdProfessional() {
        return idProfessional;
    }

    public void setIdProfessional(Integer idProfessional) {
        this.idProfessional = idProfessional;
    }

    public Integer getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Integer idPatient) {
        this.idPatient = idPatient;
    }
}
