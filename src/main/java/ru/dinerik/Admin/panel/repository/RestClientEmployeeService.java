package ru.dinerik.Admin.panel.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import ru.dinerik.Admin.panel.data.entity.Employee;

@Service
@FeignClient(name = "jsonEmployee", url = "${feign.client.url}", path = "/employee")
public interface RestClientEmployeeService extends RestClientService<Employee> {

}