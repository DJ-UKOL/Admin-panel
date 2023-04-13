package ru.dinerik.Admin.panel.service;

import ru.dinerik.Admin.panel.data.entity.Employee;
import ru.dinerik.Admin.panel.repository.RestClientEmployeeService;
import ru.dinerik.Admin.panel.repository.RestClientService;

import java.util.List;

public class EmployeeServiceImpl<T extends Employee> implements AbstractService<T>{

    RestClientService<T> service;

    @Override
    public List<T> get() {
        return service.get();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void create(T entity) {

    }

    @Override
    public void update(Long id, T entity) {

    }
}
