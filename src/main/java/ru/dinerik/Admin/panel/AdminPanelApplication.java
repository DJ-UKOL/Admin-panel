package ru.dinerik.Admin.panel;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@Theme(value = "admin-panel")
@EnableFeignClients
public class AdminPanelApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(AdminPanelApplication.class, args);
	}
}
