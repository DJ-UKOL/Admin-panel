package ru.dinerik.Admin.panel.repository;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.dinerik.Admin.panel.data.entity.AbstractEntity;

import java.util.List;

@Service
public interface RestClientService<T extends AbstractEntity> {
    // Получить все объекты
    @GetMapping
    List<T> get();
    // Получить объект по id
    @GetMapping(value = "/{id}")
    List<T> getForId(@PathVariable("id") Long id);
    // Добавить новый объект
    @PostMapping
    List<T> create(@RequestBody Object request);
    // Редактировать объект
    @PutMapping("/{id}")
    List<T> update(@PathVariable Long id, @RequestBody Object request);
    // Удалить объект
    @DeleteMapping("/{id}")
    List<T> delete(@PathVariable Long id);
}
