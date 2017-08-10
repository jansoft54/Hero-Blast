package com.shooterman.game.Component.Entity;

import com.annimon.stream.Stream;
import com.shooterman.game.Component.IComponent.IComponent;

import java.util.HashMap;

public final class EntityManager {


    private HashMap<Object, Entity> Entitys;

    public EntityManager() {
        Entitys = new HashMap<>();
    }

    public void addEntityComponent(Object id, IComponent component) {
        Entitys.get(id).addComponent(component.getClass(), component);

    }

    public void addEntityToWorld(Entity entityToAdd) {
        Entitys.put(entityToAdd.getId(), entityToAdd);
    }

    public Entity makeEntity(Object id) {
        return new Entity(id);
    }

    public Entity getEntity(Object id) {
        return Entitys.get(id);
    }

    public HashMap<Object, Entity> getEntitys() {
        return Entitys;
    }

    public IComponent getEntityComponent(Object id, Class<? extends IComponent> componentClass, boolean throwException) {
        Entity entity = Entitys.get(id);
        return entity.getComponent(componentClass, throwException);

    }

    public boolean hasEntityComponent(Object id, Class<? extends IComponent> componentClass) {
        Entity entity = Entitys.get(id);
        return entity.hasComponent(componentClass);
    }

    public synchronized void removeEntity(Object id) {
        final Object[] deadEntity = new Object[1];
        Stream.of(Entitys)
                .filter(entity -> entity.getValue().getId().equals(id))
                .forEach(entity -> {
                    deadEntity[0] = entity.getValue().getId();
                    entity.getValue().destroyEntity();
                });
        Entitys.remove(deadEntity[0]);

    }

    public void update(float dt) {
        Stream.of(Entitys).forEach(entry -> entry.getValue().update(dt));
    }
}
