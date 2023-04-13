package ru.dinerik.Admin.panel.service;

import ru.dinerik.Admin.panel.data.entity.AbstractEntity;
import ru.dinerik.Admin.panel.data.entity.Organization;
import ru.dinerik.Admin.panel.repository.RestClientOrganizationService;
import ru.dinerik.Admin.panel.repository.RestClientService;

import java.util.List;

public class OrganizationServiceImpl<T extends AbstractEntity> implements AbstractService<T> {

    RestClientService<T> service;

    public OrganizationServiceImpl() {
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
