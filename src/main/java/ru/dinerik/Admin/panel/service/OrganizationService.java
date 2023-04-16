package ru.dinerik.Admin.panel.service;

import org.springframework.stereotype.Service;
import ru.dinerik.Admin.panel.data.entity.Organization;
import ru.dinerik.Admin.panel.repository.RestClientOrganizationRepository;

@Service
public class OrganizationService extends AbstractService<Organization> {
    public OrganizationService(RestClientOrganizationRepository repository) {
        this.repository = repository;
    }
}
