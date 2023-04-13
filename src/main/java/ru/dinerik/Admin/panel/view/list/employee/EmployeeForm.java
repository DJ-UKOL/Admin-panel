package ru.dinerik.Admin.panel.view.list.employee;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import ru.dinerik.Admin.panel.data.entity.Employee;
import ru.dinerik.Admin.panel.view.list.abstraction.AbstractForm;

// Форма для создания и редактирования

public class EmployeeForm extends AbstractForm<Employee> {
  TextField lastname = new TextField("Фамилия");
  TextField firstname = new TextField("Имя");
  TextField patronymic = new TextField("Отчество");
  TextField jobTitle = new TextField("Должность");

  public EmployeeForm() {
    super(new BeanValidationBinder<>(Employee.class));
    addClassName("employee-form");
    binder.bindInstanceFields(this);
    add(firstname, lastname, patronymic, jobTitle, createButtonsLayout());
  }
}

