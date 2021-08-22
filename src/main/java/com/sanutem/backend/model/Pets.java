package com.sanutem.backend.model;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Pets")
public class Pets {

    @Id
    @SequenceGenerator(
            name="pet_sequence",
            sequenceName = "pet_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "pet_sequence"
    )
    @Column(
            name = "idPet",
            updatable = false
    )
    private Integer idPet;
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id; //hace referencia a el id del humano, hay que revisar las annotations: @ManyToOne @OneToMany
    @Column(
            name = "idUser",
            updatable = false
    )
    private Integer idUser;
    @Column(
            name = "name",
            nullable = false
    )
    private String name;
    @Column(
            name = "sex",
            nullable = false
    )
    private String sex;
    @Column(
            name = "birthday",
            nullable = false
    )
    private LocalDate birthday;
    @Column(
            name = "medicalHistory",
            nullable = false
    )
    private String medicalHistory;
    @Column(
            name = "surgeries",
            nullable = false
    )
    private String surgeries;
    @Column(
            name = "medicines",
            nullable = false
    )
    private String medicines;
    @Column(
            name = "species",
            nullable = false
    )
    private String species;
    @Column(
            name = "breed",
            nullable = false
    )
    private String breed;

    public Pets(){}

    public Pets(Integer idPet, Integer id, Integer idUser, String name,
                String sex, LocalDate birthday, String medicalHistory,
                String surgeries, String medicines, String species, String breed) {
        this.idPet = idPet;
        this.id = id;
        this.idUser = idUser;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.medicalHistory = medicalHistory;
        this.surgeries = surgeries;
        this.medicines = medicines;
        this.species = species;
        this.breed = breed;
    }

    public Integer getIdPet() {
        return idPet;
    }

    public void setIdPet(Integer idPet) {
        this.idPet = idPet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(String surgeries) {
        this.surgeries = surgeries;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
