package ru.dinerik.Admin.panel.service;

import ru.dinerik.Admin.panel.data.entity.AbstractEntity;

import java.util.List;

public interface AbstractService<T extends AbstractEntity> {

    List<T> get();

    void delete(Long id);

    void create(T entity);

    void update(Long id, T entity);
}
