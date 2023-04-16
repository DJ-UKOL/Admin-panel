package ru.dinerik.Admin.panel.service;

import org.springframework.stereotype.Service;
import ru.dinerik.Admin.panel.data.entity.Division;
import ru.dinerik.Admin.panel.repository.RestClientDivisionRepository;

@Service
public class DivisionService extends AbstractService<Division> {
    public DivisionService(RestClientDivisionRepository repository) {
        this.repository = repository;
    }
}
