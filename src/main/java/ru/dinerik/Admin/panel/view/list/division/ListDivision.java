package ru.dinerik.Admin.panel.view.list.division;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;
import ru.dinerik.Admin.panel.data.entity.Division;
import ru.dinerik.Admin.panel.repository.RestClientService;
import ru.dinerik.Admin.panel.view.MainView;
import ru.dinerik.Admin.panel.view.list.abstraction.AbstractList;


// Список подразделений
@SpringComponent
@PermitAll
@Scope("prototype")
@Route(value = "division", layout = MainView.class)
@PageTitle("Division | Admin Panel ECM")
public class ListDivision extends AbstractList<Division> {

    public ListDivision(RestClientService<Division> service) {
        super(new Grid<>(Division.class, false), new DivisionForm());
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
        grid.addColumn(Division::getFullName).setHeader("Наименование");
        grid.addColumn(Division::getContactDetails).setHeader("Контактные данные");
        grid.getColumns().forEach(e->e.setSortable(true));
    }

    @Override
    protected HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по наименованию...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateList());

        Button addContactButton = new Button("Добавить подразделение");
        addContactButton.addClickListener(click -> addEntity(new Division()));

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}