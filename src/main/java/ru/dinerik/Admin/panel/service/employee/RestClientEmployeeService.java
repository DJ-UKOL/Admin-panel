package ru.dinerik.Admin.panel.service.employee;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.Admin.panel.data.entity.Employee;

import java.util.List;

@Service
@FeignClient(name = "jsonEmployee", url = "${feign.client.url}", path = "/employee")
public interface RestClientEmployeeService {

    // Получить всех сотрудников
    @GetMapping
    List<Employee> getEmployee();
    // Получить сотрудника по id
    @GetMapping(value = "/{id}")
    List<Employee> getEmployeeId(@PathVariable("id") Long id);
    // Добавить нового сотрудника
    @PostMapping
    List<Employee> createEmployee(@RequestBody Employee request);
    // Редактировать сотрудника
    @PutMapping("/{id}")
    List<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee request);
    // Удалить сотрудника
    @DeleteMapping("/{id}")
    List<Employee> deleteEmployee(@PathVariable Long id);
}