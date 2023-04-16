package ru.dinerik.Admin.panel.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import ru.dinerik.Admin.panel.data.entity.Order;

@Service
@FeignClient(name = "jsonOrder", url = "${feign.client.url}", path = "/order")
public interface RestClientOrderRepository extends RestClientRepository<Order> {

}