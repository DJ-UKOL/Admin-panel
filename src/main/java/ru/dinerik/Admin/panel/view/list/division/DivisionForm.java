package ru.dinerik.Admin.panel.view.list.division;

import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import ru.dinerik.Admin.panel.data.entity.Division;
import ru.dinerik.Admin.panel.view.list.abstraction.AbstractForm;

public class DivisionForm extends AbstractForm<Division> {
  TextField fullName = new TextField("Наименование подразделения");
  TextField contactDetails = new TextField("Контактные данные");

  public DivisionForm() {
    super(new BeanValidationBinder<>(Division.class));
    addClassName("division-form");
    binder.bindInstanceFields(this);
    add(fullName, contactDetails, createButtonsLayout());
  }
}

