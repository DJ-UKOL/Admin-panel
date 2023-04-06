package ru.dinerik.Admin.panel.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Сотрудник
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    private Long id;
    private String lastname;        // Фамилия
    private String firstname;       // Имя
    private String patronymic;      // Отчество
    private String jobTitle;        // Должность

}
