package com.sanutem.backend.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "MedicalTests")
public class MedicalTests {

    @Id
    @SequenceGenerator(
            name="medical_test_sequence",
            sequenceName = "medical_test_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "medical_test_sequence"
    )
    @Column(
            name = "idMedicalTest",
            updatable = false
    )
    private Integer idMedicalTest;
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;//hace referencia al id del paciente humano, hay que revisar las annotations: @ManyToOne @OneToMany
    @Column(
            name = "prescription"/*,
            nullable = false*/
    )
    private String prescription;
    @Column(
            name = "details"/*,
            nullable = false*/
    )
    private String details;
    @Column(
            name = "idProfessional"/*,
            nullable = false*/
    )
    private Integer idProfessional;//hay que revisar las annotations: @ManyToOne @OneToMany

    public MedicalTests(){}

    public MedicalTests(Integer idMedicalTest, Integer id,
                        String prescription, String details, Integer idProfessional) {
        this.idMedicalTest = idMedicalTest;
        this.id = id;
        this.prescription = prescription;
        this.details = details;
        this.idProfessional = idProfessional;
    }

    public Integer getIdMedicalTest() {
        return idMedicalTest;
    }

    public void setIdMedicalTest(Integer idMedicalTest) {
        this.idMedicalTest = idMedicalTest;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getIdProfessional() {
        return idProfessional;
    }

    public void setIdProfessional(Integer idProfessional) {
        this.idProfessional = idProfessional;
    }
}
