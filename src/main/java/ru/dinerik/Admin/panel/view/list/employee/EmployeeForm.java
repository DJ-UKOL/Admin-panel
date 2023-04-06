package ru.dinerik.Admin.panel.view.list.employee;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import ru.dinerik.Admin.panel.data.entity.Employee;


public class EmployeeForm extends FormLayout {
  TextField lastname = new TextField("Фамилия");
  TextField firstname = new TextField("Имя");
  TextField patronymic = new TextField("Отчество");
  TextField jobTitle = new TextField("Должность");

  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");
  // Other fields omitted
  Binder<Employee> binder = new BeanValidationBinder<>(Employee.class);

  public EmployeeForm() {
    addClassName("employee-form");
    binder.bindInstanceFields(this);

    add(firstname,
        lastname,
        patronymic,
        jobTitle,
        createButtonsLayout());
  }

  private Component createButtonsLayout() {
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);

    save.addClickListener(event -> validateAndSave()); // <1>
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean()))); // <2>
    close.addClickListener(event -> fireEvent(new CloseEvent(this))); // <3>

    binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid())); // <4>
    return new HorizontalLayout(save, delete, close);
  }

  private void validateAndSave() {
    if(binder.isValid()) {
      fireEvent(new SaveEvent(this, binder.getBean())); // <6>
    }
  }


  public void setEmployee(Employee employee) {
    binder.setBean(employee); // <1>
  }

  // Events
  public static abstract class EmployeeFormEvent extends ComponentEvent<EmployeeForm> {
    private final Employee employee;

    protected EmployeeFormEvent(EmployeeForm source, Employee employee) {
      super(source, false);
      this.employee = employee;
    }

    public Employee getEmployee() {
      return employee;
    }
  }

  public static class SaveEvent extends EmployeeFormEvent {
    SaveEvent(EmployeeForm source, Employee employee) {
      super(source, employee);
    }
  }

  public static class DeleteEvent extends EmployeeFormEvent {
    DeleteEvent(EmployeeForm source, Employee employee) {
      super(source, employee);
    }

  }

  public static class CloseEvent extends EmployeeFormEvent {
    CloseEvent(EmployeeForm source) {
      super(source, null);
    }
  }

  public Registration addDeleteListener(ComponentEventListener<DeleteEvent> listener) {
    return addListener(DeleteEvent.class, listener);
  }

  public Registration addSaveListener(ComponentEventListener<SaveEvent> listener) {
    return addListener(SaveEvent.class, listener);
  }
  public Registration addCloseListener(ComponentEventListener<CloseEvent> listener) {
    return addListener(CloseEvent.class, listener);
  }


}

