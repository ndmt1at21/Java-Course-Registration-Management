package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "configuration")
public class Configuration {
    @Id
    @Column(name = "setting_name")
    private String settingName;

    @Column(name = "data_type", nullable = false)
    private String dataType;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "notes")
    private String notes;

    @Column(name = "status")
    @Builder.Default
    @NotNull
    private boolean isActive = true;

    public <T> Configuration(String settingName, T value, String notes) {
        this.settingName = settingName;
        this.dataType = value.getClass().getName();
        this.value = value.toString();
        this.notes = notes;
    }

    public Configuration(String settingName, String dataType, String value) {
        this.settingName = settingName;
        this.dataType = dataType;
        this.value = value;
    }

    public String getSettingName() {
        return this.settingName;
    }
}