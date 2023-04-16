package ru.dinerik.Admin.panel.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import ru.dinerik.Admin.panel.data.entity.Organization;

@Service
@FeignClient(name = "jsonOrganization", url = "${feign.client.url}", path = "/organization")
public interface RestClientOrganizationRepository extends RestClientRepository<Organization> {

}