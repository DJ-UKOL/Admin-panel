package ru.dinerik.Admin.panel.service;

import org.springframework.stereotype.Service;
import ru.dinerik.Admin.panel.data.entity.Order;
import ru.dinerik.Admin.panel.repository.RestClientOrderRepository;

@Service
public class OrderService extends AbstractService<Order> {
    public OrderService(RestClientOrderRepository repository) {
        this.repository = repository;
    }
}