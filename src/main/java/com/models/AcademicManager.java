package com.models;

import java.util.UUID;

import javax.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "academic_mamanger")
public class AcademicManager extends User {
    @Column(name = "academic_id", updatable = false)
    @Setter(value = AccessLevel.NONE)
    private String academicManagerID;

    @PrePersist
    private void generateAcademicID() {
        this.academicManagerID = UUID.randomUUID().toString().substring(0, 9).replace("-", "");
    }
}