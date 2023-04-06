package ru.dinerik.Admin.panel;

import com.vaadin.flow.component.page.AppShellConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdminPanelApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(AdminPanelApplication.class, args);
	}
}
