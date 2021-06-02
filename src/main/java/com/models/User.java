package com.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.constants.Sex;

import org.hibernate.annotations.GenericGenerator;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "`User`")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(updatable = false)
    private String id;

    @Column
    @NotNull
    @Size(min = 5, max = 20, message = "Username must between 5 and 20 characters")
    private String username;

    @Column
    @NotNull
    @Size(min = 8, message = "Password too short")
    private String password;

    @Column
    @NotNull
    @Size(max = 30, message = "First name is too long")
    private String firstName;

    @Column
    @NotNull
    @Size(max = 100, message = "Last name is too long")
    private String lastName;

    @Column
    @NotNull
    @Size(max = 500, message = "Address is too long")
    private String address;

    @Column
    @NotNull
    private Date birth;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Column
    @NotNull
    @Min(1900)
    @Max(3000)
    private int startYear;

    @PrePersist
    @PreUpdate
    private void transformData() {
        this.trimString();
        this.enryptPassword();
    }

    private void trimString() {
        this.username = this.username.trim().toLowerCase();
        this.firstName = this.firstName.trim().toUpperCase();
        this.lastName = this.lastName.trim().toUpperCase();
        this.address = this.address.trim();
    }

    private void enryptPassword() {
        this.password = BCrypt.withDefaults().hashToString(12, this.password.toCharArray());
    }

    public User() {
    }

    public User(String username, String password, String firstName, String lastName, String address, Date birth,
            Sex sex, int startYear) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birth = birth;
        this.sex = sex;
        this.startYear = startYear;
    }

    public int getStartYear() {
        return this.startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    protected String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirth() {
        return this.birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Sex getSex() {
        return this.sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public boolean checkPassword(String password) {
        String enryptPass = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        return enryptPass == this.password;
    }
}