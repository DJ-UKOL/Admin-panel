package ru.dinerik.Admin.panel.view.list.abstraction;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import ru.dinerik.Admin.panel.data.entity.AbstractEntity;
import ru.dinerik.Admin.panel.service.AbstractService;

import java.util.List;

public abstract class AbstractList<T extends AbstractEntity> extends VerticalLayout {
    protected TextField filterText = new TextField();
    protected Grid<T> grid;
    protected AbstractForm<T> form;
    protected AbstractService<T> service;
    protected abstract void configureGrid();
    protected abstract HorizontalLayout getToolbar();

    public AbstractList(Grid<T> grid, AbstractForm<T> form) {
        this.grid = grid;
        this.form = form;
    }

    protected Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    protected void updateList() {
        grid.setItems(service.findAll(filterText.getValue()));
    }

    protected void closeEditor() {
        form.setEntity(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    protected void deleteEntity(AbstractForm.DeleteEvent event) {
        service.delete(event.getEntity().getId());
        updateList();
        closeEditor();
    }

    protected void saveEntity(AbstractForm.SaveEvent event) {
        if(event.getEntity().getId() == null) {
            service.save(event.getEntity());
        }
        else {
            service.update(event.getEntity().getId(), event.getEntity());
        }
        updateList();
        closeEditor();
    }

    protected void editEntity(T entity) {
        if (entity == null) {
            closeEditor();
        } else {
            form.setEntity(entity);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    protected void addEntity(T entity) {
        grid.asSingleSelect().clear();
        editEntity(entity);
    }

    protected void configureForm() {
        form.setWidth("25em");
        form.addListener(AbstractForm.SaveEvent.class, this::saveEntity);
        form.addListener(AbstractForm.DeleteEvent.class, this::deleteEntity);
        form.addListener(AbstractForm.CloseEvent.class, e -> closeEditor());
    }

    protected void initGrid() {
        grid.addClassNames("abstract-grid");
        List<T> list = service.findAll();
        grid.setItems(list);
        grid.setSizeFull();

        grid.asSingleSelect().addValueChangeListener(event ->
                editEntity(event.getValue()));
    }
}