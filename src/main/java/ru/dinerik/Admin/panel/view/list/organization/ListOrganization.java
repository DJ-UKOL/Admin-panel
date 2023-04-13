package ru.dinerik.Admin.panel.view.list.organization;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;
import ru.dinerik.Admin.panel.data.entity.Organization;
import ru.dinerik.Admin.panel.repository.RestClientService;
import ru.dinerik.Admin.panel.view.MainView;
import ru.dinerik.Admin.panel.view.list.abstraction.AbstractList;

// Список организаций
@SpringComponent
@PermitAll
@Scope("prototype")
@Route(value = "organization", layout = MainView.class)
@PageTitle("Organization | Admin Panel ECM")
public class ListOrganization extends AbstractList<Organization> {

    public ListOrganization(RestClientService<Organization> service) {
        super(new Grid<>(Organization.class, false), new OrganizationForm());
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
        grid.addColumn(Organization::getFullName).setHeader("Наименование организации");
        grid.addColumn(Organization::getLegalAddress).setHeader("Юридический адрес");
        grid.addColumn(Organization::getPostalAddress).setHeader("Физический адрес");
        grid.getColumns().forEach(e->e.setSortable(true));
    }

    @Override
    protected HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по наименованию...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateList());

        Button addContactButton = new Button("Добавить подразделение");
        addContactButton.addClickListener(click -> addEntity(new Organization()));

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}