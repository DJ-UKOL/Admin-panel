package ru.dinerik.Admin.panel.view.list.abstraction;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import ru.dinerik.Admin.panel.data.entity.AbstractEntity;

public abstract class AbstractForm<T extends AbstractEntity> extends FormLayout {
    protected Button save = new Button("Save");
    protected Button delete = new Button("Delete");
    protected Button close = new Button("Cancel");
    protected Binder<T> binder;

    protected AbstractForm(Binder<T> binder) {
        this.binder = binder;
    }

    protected Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    // Events
    public static abstract class AbstractFormEvent extends ComponentEvent<AbstractForm<? extends AbstractEntity>> {
        private final AbstractEntity entity;
        protected AbstractFormEvent(AbstractForm<?> source, AbstractEntity entity) {
            super(source, false);
            this.entity = entity;
        }

        public AbstractEntity getEntity() {
            return entity;
        }
    }

    public void setEntity(T entity) {
        binder.setBean(entity);
    }

    private void validateAndSave() {
        if(binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    public static class SaveEvent extends AbstractForm.AbstractFormEvent {
        SaveEvent(AbstractForm<? extends AbstractEntity> source, AbstractEntity abstractEntity) {
            super(source, abstractEntity);
        }
    }

    public static class DeleteEvent extends AbstractForm.AbstractFormEvent {
        DeleteEvent(AbstractForm<? extends AbstractEntity> source, AbstractEntity abstractEntity) {
            super(source, abstractEntity);
        }
    }

    public static class CloseEvent extends AbstractForm.AbstractFormEvent {
        CloseEvent(AbstractForm<? extends AbstractEntity> source) {
            super(source, null);
        }
    }

    public <C extends ComponentEvent<?>> Registration addListener(Class<C> eventType,
                                                                  ComponentEventListener<C> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}