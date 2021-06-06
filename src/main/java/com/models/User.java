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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "`User`")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(updatable = false)
    private String userID;

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

    @Column(updatable = false)
    @CreationTimestamp
    private Date createdAt;

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

    public boolean checkPassword(String password) {
        String enryptPass = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        return enryptPass == this.password;
    }
}