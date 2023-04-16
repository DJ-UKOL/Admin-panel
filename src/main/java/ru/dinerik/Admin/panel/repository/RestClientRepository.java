package ru.dinerik.Admin.panel.repository;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.Admin.panel.data.entity.AbstractEntity;

import java.util.List;

@Service
public interface RestClientRepository<T extends AbstractEntity> {
    // Получить все объекты
    @GetMapping
    List<T> get();
    // Получить объект по id
    @GetMapping(value = "/{id}")
    List<T> getForId(@PathVariable("id") Long id);
    // Добавить новый объект
    @PostMapping
    List<T> create(@RequestBody AbstractEntity request);
    // Редактировать объект
    @PutMapping("/{id}")
    List<T> update(@PathVariable Long id, @RequestBody AbstractEntity request);
    // Удалить объект
    @DeleteMapping("/{id}")
    List<T> delete(@PathVariable Long id);
    @GetMapping("/search")
    List<T> searchOnText(@RequestParam("searchText") String searchText);
}