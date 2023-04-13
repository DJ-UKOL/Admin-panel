package ru.dinerik.Admin.panel.view.list.order;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.Scope;
import ru.dinerik.Admin.panel.data.entity.Order;
import ru.dinerik.Admin.panel.repository.RestClientService;
import ru.dinerik.Admin.panel.view.MainView;
import ru.dinerik.Admin.panel.view.list.abstraction.AbstractList;


// Список поручений
@SpringComponent
@PermitAll
@Scope("prototype")
@Route(value = "order", layout = MainView.class)
@PageTitle("Order | Admin Panel ECM")
public class ListOrder extends AbstractList<Order> {

    public ListOrder(RestClientService<Order> service) {
        super(new Grid<>(Order.class, false), new OrderForm());
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
        grid.addColumn(Order::getSubject).setHeader("Предмет поручения");
        grid.addColumn(Order::getTimeExecution).setHeader("Срок исполнения");
        grid.addColumn(Order::getText).setHeader("Текст поручения");
        grid.getColumns().forEach(e->e.setSortable(true));
    }

    @Override
    protected HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по предмету...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e->updateList());

        Button addContactButton = new Button("Добавить поручение");
        addContactButton.addClickListener(click -> addEntity(new Order()));

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}
