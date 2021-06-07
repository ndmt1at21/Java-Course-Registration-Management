package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public Configuration(String settingName, String dataType, String value) {
        this.settingName = settingName;
        this.dataType = dataType;
        this.value = value;
    }

    public String getSettingName() {
        return this.settingName;
    }
}