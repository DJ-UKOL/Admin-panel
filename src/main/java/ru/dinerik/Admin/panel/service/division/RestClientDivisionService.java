package ru.dinerik.Admin.panel.service.division;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.dinerik.Admin.panel.data.entity.Division;

import java.util.List;

@Service
@FeignClient(name = "jsonDivision", url = "${feign.client.url}", path = "/division")
public interface RestClientDivisionService {

    // Получить все подразделения
    @GetMapping
    List<Division> getDivision();
    // Получить подразделение по id
    @GetMapping(value = "/{id}")
    List<Division> getDivisionId(@PathVariable("id") Long id);
}