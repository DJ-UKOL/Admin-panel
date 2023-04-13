package ru.dinerik.Admin.panel.view.list.order;

import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import ru.dinerik.Admin.panel.data.entity.Order;
import ru.dinerik.Admin.panel.view.list.abstraction.AbstractForm;

public class OrderForm extends AbstractForm<Order> {

  TextField subject = new TextField("Предмет поручения");
  DateTimePicker timeExecution = new DateTimePicker("Срок исполнения");
  TextField text = new TextField("Текст поручения");

  public OrderForm() {
    super(new BeanValidationBinder<>(Order.class));
    addClassName("order-form");
    binder.forField(timeExecution).bind(Order::getTimeExecution, Order::setTimeExecution);
    binder.bindInstanceFields(this);
    add(subject, timeExecution, text, createButtonsLayout());
  }
}

