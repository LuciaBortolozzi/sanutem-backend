package com.sanutem.backend.model;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Users")
@Table(
        name = "users",
        uniqueConstraints ={
                @UniqueConstraint(name = "user_name", columnNames = "userName"),
                @UniqueConstraint(name = "dni", columnNames = "dni")
        }
)
public class Users {

    @Id
    @SequenceGenerator(
            name="user_sequence",
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
            name = "dni",
            nullable = false
    )
    //@Column(unique = true)
    private String dni;
    @Column(
            name = "firstName",
            nullable = false
    )
    private String firstName;
    @Column(
            name = "lastName",
            nullable = false
    )
    private String lastName;
    @Column(
            name = "userName",
            nullable = false
    )
    //@Column(unique = true)
    private String userName;
    @Column(
            name = "email",
            nullable = false
    )
    private String email;
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
            name = "address",
            nullable = false
    )
    private String address;

    public Users() {
    }

    public Users(Integer id, String dni, String firstName, String lastName, String userName, String email, String sex, LocalDate birthday, String address) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String apellido) {
        this.lastName = apellido;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sexo) {
        this.sex = sexo;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate fechaNacimiento) {
        this.birthday = fechaNacimiento;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String direccion) {
        this.address = direccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}