package ru.dinerik.Admin.panel.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import ru.dinerik.Admin.panel.security.config.SecurityService;
import ru.dinerik.Admin.panel.view.list.division.ListDivision;
import ru.dinerik.Admin.panel.view.list.employee.ListEmployee;
import ru.dinerik.Admin.panel.view.list.order.ListOrder;
import ru.dinerik.Admin.panel.view.list.organization.ListOrganization;


public class MainView extends AppLayout {

    private final SecurityService securityService;

    public MainView(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H3 logo = new H3("Панель администратора ECM");

        String u = securityService.getAuthenticatedUser().getUsername();
        Button logout = new Button("Log out " + u, e -> securityService.logout());

        var header = new HorizontalLayout(
                new DrawerToggle(), logo, logout);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);
        addToNavbar(header);
    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                new RouterLink("Сотрудники", ListEmployee.class),
                new RouterLink("Организации", ListOrganization.class),
                new RouterLink("Подразделения", ListDivision.class),
                new RouterLink("Поручения", ListOrder.class)
        ));
    }
}