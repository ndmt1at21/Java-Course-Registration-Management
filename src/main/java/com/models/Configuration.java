package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Configuration {
    @Id
    @Column
    private String settingName;

    @Column(nullable = false)
    private String dataType;

    @Column(nullable = false)
    private String value;

    @Column
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