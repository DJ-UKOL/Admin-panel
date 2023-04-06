package ru.dinerik.Admin.panel.service.organization;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.dinerik.Admin.panel.data.entity.Employee;
import ru.dinerik.Admin.panel.data.entity.Organization;

import java.util.List;

@Service
@FeignClient(name = "jsonOrganization", url = "${feign.client.url}", path = "/organization")
public interface RestClientOrganizationService {

    // Получить все организации
    @GetMapping
    List<Organization> getOrganization();
    // Получить организацию по id
    @GetMapping(value = "/{id}")
    List<Organization> getOrganizationId(@PathVariable("id") Long id);
}