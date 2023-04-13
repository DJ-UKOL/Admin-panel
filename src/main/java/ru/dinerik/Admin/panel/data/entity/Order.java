package ru.dinerik.Admin.panel.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// Поручение
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order extends AbstractEntity {

    private String subject;                         // Предмет поручения
    private LocalDateTime timeExecution;            // Срок исполнения
    private String text;                            // Текст поручения
}
