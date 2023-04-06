package ru.dinerik.Admin.panel.view.list.order;

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
import ru.dinerik.Admin.panel.data.entity.Order;
import ru.dinerik.Admin.panel.service.order.RestClientOrderService;
import ru.dinerik.Admin.panel.view.MainView;

import java.util.List;

// Список поручений
@SpringComponent
@PermitAll
@Scope("prototype")
@Route(value = "order", layout = MainView.class)
@PageTitle("Order | Admin Panel ECM")
public class ListOrder extends VerticalLayout {
    private final RestClientOrderService restClientOrderService;
    Grid<Order> grid = new Grid<>(Order.class, false);
    TextField filterText = new TextField();

    public ListOrder(RestClientOrderService restClientOrderService) {
        this.restClientOrderService = restClientOrderService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(getToolbar(), grid);
    }

    private void configureGrid() {
        List<Order> list = restClientOrderService.getOrder();
        grid.setItems(list);
        grid.addColumn(Order::getId).setHeader("№");
        grid.addColumn(Order::getSubject).setHeader("Предмет поручения");
        grid.addColumn(Order::getTimeExecution).setHeader("Срок исполнения");
        grid.addColumn(Order::getText).setHeader("Текс поручения");
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Фильтр по предмету...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addContactButton = new Button("Добавить поручение");

        var toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }
}
