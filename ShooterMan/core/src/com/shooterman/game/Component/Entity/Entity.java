package com.shooterman.game.Component.Entity;

import com.annimon.stream.Stream;
import com.shooterman.game.Component.IComponent.IComponent;

import java.util.HashMap;
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

    public IComponent getComponent(Class<? extends IComponent> component,boolean throwException) {
        IComponent foundComponent = Components.get(component);
        if (foundComponent == null && throwException)
            throw new IllegalArgumentException("Requested component was not found");
        else return foundComponent;
    }

    public boolean hasComponent(Class<? extends IComponent> component) {
        return Components.get(component) != null ;
    }

    public Object getId() {
        return id;
    }

    public void update(float dt) {

        Stream.of(Components).forEach(component -> component.getValue().update(dt));
    }

    void destroyEntity() {
        Stream.of(Components).forEach(component -> component.getValue().clearData());
        Components = null;
    }
}
