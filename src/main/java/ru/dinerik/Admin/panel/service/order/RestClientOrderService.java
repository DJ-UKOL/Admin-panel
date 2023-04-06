package ru.dinerik.Admin.panel.service.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.dinerik.Admin.panel.data.entity.Order;
import ru.dinerik.Admin.panel.data.entity.Organization;

import java.util.List;

@Service
@FeignClient(name = "jsonOrder", url = "${feign.client.url}", path = "/order")
public interface RestClientOrderService {
    // Получить все поручения
    @GetMapping
    List<Order> getOrder();
    // Получить поручение по id
    @GetMapping(value = "/{id}")
    List<Order> getOrderId(@PathVariable("id") Long id);
}