package ru.dinerik.Admin.panel.view.list.employee;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;
import ru.dinerik.Admin.panel.data.entity.Employee;
import ru.dinerik.Admin.panel.service.employee.RestClientEmployeeService;
import ru.dinerik.Admin.panel.view.MainView;

import java.util.List;

// Список пользователей
@SpringComponent
@PermitAll
@Scope("prototype")
@Route(value = "", layout = MainView.class)
@PageTitle("Employee | Admin Panel ECM")
public class ListEmployee extends VerticalLayout {

    private final RestClientEmployeeService restClientEmployeeService;
    Grid<Employee> grid = new Grid<>(Employee.class, false);
    TextField filterText = new TextField();
    EmployeeForm form;

    public ListEmployee(RestClientEmployeeService restClientEmployeeService) {
        this.restClientEmployeeService = restClientEmployeeService;

        addClassName("list-view");
        setSizeFull();

        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new EmployeeForm();
        form.setWidth("25em");
        form.addSaveListener(this::saveEmployee);
        form.addDeleteListener(this::deleteEmployee);
        form.addCloseListener(e -> closeEditor());
    }

    private void saveEmployee(EmployeeForm.SaveEvent event) {
        if(event.getEmployee().getId() == null) {
            restClientEmployeeService.createEmployee(event.getEmployee());
        }
        else {
            restClientEmployeeService.updateEmployee(event.getEmployee().getId(), event.getEmployee());
        }
        updateList();
        closeEditor();
    }

    private void deleteEmployee(EmployeeForm.DeleteEvent event) {
        restClientEmployeeService.deleteEmployee(event.getEmployee().getId());
        updateList();
        closeEditor();
    }

    public void editEmployee(Employee employee) {
        if (employee == null) {
            closeEditor();
        } else {
            form.setEmployee(employee);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setEmployee(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void addEmployee() {
        grid.asSingleSelect().clear();
        editEmployee(new Employee());
    }

    private void updateList() {
        grid.setItems(restClientEmployeeService.getEmployee());
    }

    private void configureGrid() {
        List<Employee> list = restClientEmployeeService.getEmployee();
        grid.setItems(list);
        grid.addColumn(Employee::getId).setHeader("№");
        grid.addColumn(Employee::getLastname).setHeader("Фамилия");
        grid.addColumn(Employee::getFirstname).setHeader("Имя");
        grid.addColumn(Employee::getPatronymic).setHeader("Отчество");
        grid.addColumn(Employee::getJobTitle).setHeader("Должность");

        grid.asSingleSelect().addValueChangeListener(event ->
                editEmployee(event.getValue()));
    }

    private Component getToolbar() {
        filterText.setPlaceholder("Фильтр по имени...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateList());

        Button addContactButton = new Button("Добавить сотрудника");
        addContactButton.addClickListener(click -> addEmployee());

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}