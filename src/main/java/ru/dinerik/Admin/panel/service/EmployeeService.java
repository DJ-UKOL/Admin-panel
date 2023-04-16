package ru.dinerik.Admin.panel.service;

import org.springframework.stereotype.Service;
import ru.dinerik.Admin.panel.data.entity.Employee;
import ru.dinerik.Admin.panel.repository.RestClientEmployeeRepository;

@Service
public class EmployeeService extends AbstractService<Employee> {
    public EmployeeService(RestClientEmployeeRepository repository) {
        this.repository = repository;
    }
}
