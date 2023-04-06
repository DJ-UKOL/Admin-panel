package ru.dinerik.Admin.panel.view.list.organization;

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
import ru.dinerik.Admin.panel.data.entity.Organization;
import ru.dinerik.Admin.panel.service.organization.RestClientOrganizationService;
import ru.dinerik.Admin.panel.view.MainView;

import java.util.List;

// Список организаций
@SpringComponent
@PermitAll
@Scope("prototype")
@Route(value = "organization", layout = MainView.class)
@PageTitle("Organization | Admin Panel ECM")
public class ListOrganization extends VerticalLayout {

    private final RestClientOrganizationService restClientOrganizationService;
    Grid<Organization> grid = new Grid<>(Organization.class, false);
    TextField filterText = new TextField();

    public ListOrganization(RestClientOrganizationService restClientOrganizationService) {
        this.restClientOrganizationService = restClientOrganizationService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
    }

    private void configureGrid() {
        List<Organization> list = restClientOrganizationService.getOrganization();
        grid.setItems(list);
        grid.addColumn(Organization::getId).setHeader("№");
        grid.addColumn(Organization::getFullName).setHeader("Наименование");
        grid.addColumn(Organization::getPostalAddress).setHeader("Физический адрес");
        grid.addColumn(Organization::getLegalAddress).setHeader("Юридический адрес");
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по наименованию...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addContactButton = new Button("Добавить организацию");

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}

