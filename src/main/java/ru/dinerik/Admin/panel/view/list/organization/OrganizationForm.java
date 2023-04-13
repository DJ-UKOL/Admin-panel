package ru.dinerik.Admin.panel.view.list.organization;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import ru.dinerik.Admin.panel.data.entity.Organization;
import ru.dinerik.Admin.panel.view.list.abstraction.AbstractForm;

public class OrganizationForm extends AbstractForm<Organization> {
  TextField fullName = new TextField("Наименование организации");
  TextField postalAddress = new TextField("Физический адрес");
  TextField legalAddress = new TextField("Юридический адрес");

  public OrganizationForm() {
    super(new BeanValidationBinder<>(Organization.class));
    addClassName("organization-form");
    binder.bindInstanceFields(this);
    add(fullName, postalAddress, legalAddress, createButtonsLayout());
  }
}