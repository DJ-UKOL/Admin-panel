package ru.dinerik.Admin.panel.view.list.division;

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
import ru.dinerik.Admin.panel.data.entity.Division;
import ru.dinerik.Admin.panel.service.division.RestClientDivisionService;
import ru.dinerik.Admin.panel.view.MainView;

import java.util.List;

// Список подразделений
@SpringComponent
@PermitAll
@Scope("prototype")
@Route(value = "division", layout = MainView.class)
@PageTitle("Division | Admin Panel ECM")
public class ListDivision extends VerticalLayout {
    private final RestClientDivisionService restClientDivisionService;
    Grid<Division> grid = new Grid<>(Division.class, false);
    TextField filterText = new TextField();

    public ListDivision(RestClientDivisionService restClientDivisionService) {
        this.restClientDivisionService = restClientDivisionService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
    }

    private void configureGrid() {
        List<Division> list = restClientDivisionService.getDivision();
        grid.setItems(list);
        grid.addColumn(Division::getId).setHeader("№");
        grid.addColumn(Division::getFullName).setHeader("Наименование");
        grid.addColumn(Division::getContactDetails).setHeader("Контактные данные");
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по наименованию...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addContactButton = new Button("Добавить подразделение");

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}
