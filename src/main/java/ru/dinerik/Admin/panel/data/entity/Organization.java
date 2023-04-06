package ru.dinerik.Admin.panel.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Организация
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Organization {

    private Long id;
    private String fullName;            // Наименование организации
    private String postalAddress;       // Физический адрес
    private String legalAddress;        // Юридический адрес
}
