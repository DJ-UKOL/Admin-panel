package ru.dinerik.Admin.panel.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import ru.dinerik.Admin.panel.data.entity.Division;

@Service
@FeignClient(name = "jsonDivision", url = "${feign.client.url}", path = "/division")
public interface RestClientDivisionRepository extends RestClientRepository<Division> {

}