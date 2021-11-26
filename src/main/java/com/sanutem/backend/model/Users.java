package com.sanutem.backend.model;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Users")
/*
@Table(
        name = "users",
        uniqueConstraints ={
                @UniqueConstraint(name = "user_name", columnNames = "username"),
                @UniqueConstraint(name = "dni", columnNames = "dni")
        }
)
*/
public class Users {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;
    @Column(
            name = "dni"/*,
            nullable = false*/
    )
    private String dni;
    @Column(
            name = "firstName"/*,
            nullable = false*/
    )
    private String firstName;
    @Column(
            name = "lastName"/*,
            nullable = false*/
    )
    private String lastName;
    @Column(
            name = "username"/*,
            nullable = false*/
    )
    private String username;
    @Column(
            name = "email"/*,
            nullable = false*/
    )
    private String email;
    @Column(
            name = "sex"/*,
            nullable = false*/
    )
    private String sex;
    @Column(
            name = "birthday"/*,
            nullable = false*/
    )
    private LocalDate birthday;
    @Column(
            name = "address"/*,
            nullable = false*/
    )
    private String address;
    @Column(
            name = "password"/*,
            nullable = false*/
    )
    private String password;
    @Column(
            name = "created"/*,
            nullable = false*/
    )
    private Instant created;
    @Column(
            name = "enabled"/*,
            nullable = false*/
    )
    private boolean enabled;
    @Column(
            name = "role"/*,
            nullable = false*/
    )
    private String role;

    @Column(
            name = "blood_type"/*,
            nullable = false*/
    )
    private String blood_type;
    @Column(
            name = "medical_history"/*,
            nullable = false*/
    )
    private String medical_history;
    @Column(
            name = "surgeries"/*,
            nullable = false*/
    )
    private String surgeries;
    @Column(
            name = "medicines"/*,
            nullable = false*/
    )
    private String medicines;
    @Column(
            name = "license_number"/*,
            nullable = false*/
    )
    private String license_number;
    @Column(
            name = "specialization"/*,
            nullable = false*/
    )
    private String specialization;
    @Column(
            name = "province"/*,
            nullable = false*/
    )
    private String province;
    @Column(
            name = "healthInsurances"/*,
            nullable = false*/
    )
    private String healthInsurances;

    public Users() {
    }

    public Users(Integer id, String dni, String firstName, String lastName,
                 String username, String email, String sex, LocalDate birthday,
                 String address, String password, Instant created, boolean enabled,
                 String role, String blood_type, String medical_history, String surgeries,
                 String medicines, String license_number, String specialization,
                 String province, String healthInsurances) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
        this.password = password;
        this.created = created;
        this.enabled = enabled;
        this.role = role;
        this.blood_type = blood_type;
        this.medical_history = medical_history;
        this.surgeries = surgeries;
        this.medicines = medicines;
        this.license_number = license_number;
        this.specialization = specialization;
        this.province = province;
        this.healthInsurances = healthInsurances;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getHomeAddress() {
        return address;
    }

    public void setHomeAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getMedical_history() {
        return medical_history;
    }

    public void setMedical_history(String medical_history) {
        this.medical_history = medical_history;
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

    public String getLicense_number() {
        return license_number;
    }

    public void setLicense_number(String license_number) {
        this.license_number = license_number;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getHealthInsurances() {
        return healthInsurances;
    }

    public void setHealthInsurances(String healthInsurances) {
        this.healthInsurances = healthInsurances;
    }
}
