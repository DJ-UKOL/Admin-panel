package ru.dinerik.Admin.panel.service;

import org.springframework.stereotype.Service;
import ru.dinerik.Admin.panel.data.entity.AbstractEntity;
import ru.dinerik.Admin.panel.repository.RestClientRepository;

import java.util.List;

@Service
public class AbstractService<T extends AbstractEntity> {

    protected RestClientRepository<T> repository;

    public List<T> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return repository.get();
        } else {
            return repository.searchOnText(stringFilter);
        }
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    public void save(AbstractEntity entity) {
        if (entity == null) {
            System.err.println("Null. Are you sure you have connected your form to the application?");
            return;
        }
        repository.create(entity);
    }

    public void update(Long id, AbstractEntity entity) {
        if (entity == null) {
            System.err.println("Null. Are you sure you have connected your form to the application?");
            return;
        }
        repository.update(id, entity);
    }

    public List<T> findAll() {
        return repository.get();
    }
}
