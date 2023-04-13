package ru.dinerik.Admin.panel.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Подразделение
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Division extends AbstractEntity {
    private String fullName;            // Наименование подразделения
    private String contactDetails;      // Контактные данные
}
