package ru.dinerik.Admin.panel.view.list.employee;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;
import ru.dinerik.Admin.panel.data.entity.Employee;
import ru.dinerik.Admin.panel.service.AbstractService;
import ru.dinerik.Admin.panel.view.MainView;
import ru.dinerik.Admin.panel.view.list.abstraction.AbstractList;

// Список пользователей
@SpringComponent
@PermitAll
@Scope("prototype")
@Route(value = "", layout = MainView.class)
@PageTitle("Employee | Admin Panel ECM")
public class ListEmployee extends AbstractList<Employee> {

    public ListEmployee(AbstractService<Employee> service) {
        super(new Grid<>(Employee.class, false), new EmployeeForm());
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();
        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    @Override
    protected void configureGrid() {
        initGrid();
        grid.addColumn(Employee::getLastname).setHeader("Фамилия");
        grid.addColumn(Employee::getFirstname).setHeader("Имя");
        grid.addColumn(Employee::getPatronymic).setHeader("Отчество");
        grid.addColumn(Employee::getJobTitle).setHeader("Должность");
        grid.getColumns().forEach(e->e.setSortable(true));
    }

    @Override
    protected HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по имени...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateList());

        Button addContactButton = new Button("Добавить сотрудника");
        addContactButton.addClickListener(click -> addEntity(new Employee()));

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}