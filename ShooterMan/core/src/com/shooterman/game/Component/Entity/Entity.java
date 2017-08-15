package com.shooterman.game.Component.Entity;

import com.annimon.stream.Stream;
import com.shooterman.game.Component.IComponent.IComponent;

import java.util.LinkedHashMap;


public final class Entity {
    private LinkedHashMap<Class<? extends IComponent>, IComponent> Components;
    private Object id;

    Entity(Object id) {
        Components = new LinkedHashMap<>();
        this.id = id;
    }

    void addComponent(Class<? extends IComponent> componentClass, IComponent component) {
        Components.put(componentClass, component);
    }

    public IComponent getComponent(Class<? extends IComponent> component, boolean throwException) {
        IComponent foundComponent = Components.get(component);
        if (foundComponent == null && throwException)
            throw new IllegalArgumentException("Requested component was not found "+ component.getClass());
        else return foundComponent;
    }

    public boolean hasComponent(Class<? extends IComponent> component) {
        return Stream.of(Components.values()).filter(component::isInstance).count() > 0;
    }

    public Object getId() {
        return id;
    }

    public void update(float dt) {
        Stream.of(Components.values()).forEach(component -> component.update(dt));
    }

    void destroyEntity() {
        /*Using an unbound non-static method reference*/
        Stream.of(Components.values()).forEach(IComponent::clearData);
        Components = null;
    }

}
