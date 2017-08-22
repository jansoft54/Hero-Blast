package com.shooterman.game.Component.Entity;


import com.annimon.stream.Stream;
import com.shooterman.game.Component.IComponent.IComponent;

import java.util.HashMap;
import java.util.Map;


public final class Entity {
    private HashMap<Class<? extends IComponent>, IComponent> Components;
    private Object id;

    Entity(Object id) {
        Components = new HashMap<>();
        this.id = id;

    }

    synchronized void addComponent(Class<? extends IComponent> componentClass, IComponent component) {
        Components.put(componentClass, component);
    }

    public IComponent getComponent(Class<? extends IComponent> component) {
        IComponent foundComponent = null;
        for (Map.Entry<Class<? extends IComponent>, IComponent> entry : Components.entrySet()) {
            String nameOfStoredKey = entry.getKey().getName();
            String nameOfRequestedKey = component.getName();
            if (nameOfStoredKey.contains(nameOfRequestedKey.substring(30, nameOfRequestedKey.length() - 9)))
                foundComponent = entry.getValue();
        }
        if (foundComponent == null)
            throw new IllegalArgumentException("Requested component was not found " + component.getClass());
        else return foundComponent;
    }

    public boolean hasComponent(Class<? extends IComponent> component) {
        return Stream.of(Components.values()).filter(component::isInstance).count() > 0;
    }

    public Object getId() {
        return id;
    }

    public void update(float dt) {
         /* These are operations called 60 times/sec creating a Stream object for every invocation is not efficient*/
        for (IComponent component: Components.values())
             component.update(dt);
    }



    void destroyEntity() {
        /*Using an unbound non-static(instance) method reference*/
        Stream.of(Components.values()).forEach(IComponent::clearData);
        Components = null;
    }

}
