package ru.dinerik.Admin.panel.service;

import ru.dinerik.Admin.panel.data.entity.AbstractEntity;
import ru.dinerik.Admin.panel.data.entity.Order;
import ru.dinerik.Admin.panel.repository.RestClientOrderService;
import ru.dinerik.Admin.panel.repository.RestClientService;

import java.util.List;

public class OrderServiceImpl<T extends AbstractEntity> implements AbstractService<T> {

    RestClientService<T> service;

    public OrderServiceImpl() {
    }

    @Override
    public List<T> get() {
        return service.get();
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public void create(T entity) {
        service.create(entity);
    }

    @Override
    public void update(Long id, T entity) {
        service.update(id, entity);
    }
}