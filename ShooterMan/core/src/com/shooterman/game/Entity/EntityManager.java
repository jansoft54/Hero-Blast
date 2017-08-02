package com.shooterman.game.Entity;


import com.annimon.stream.Stream;
import com.shooterman.game.Component.IComponent.IComponent;
import java.util.HashMap;

public class EntityManager {
    private HashMap<Object,Entity> Entitys;
    public EntityManager()
    {
        Entitys = new HashMap<>();
    }

    public void addEntityComponent(Object id,IComponent component)
    {
       Entitys.get(id).addComponent(component.getClass(),component);
    }
    public void addEntityToWorld(Entity entityToAdd)
    {
        Entitys.put(entityToAdd.getId(),entityToAdd);
    }
    public Entity makeEntity(Object id)
    {
        return new Entity(id);
    }
    public Entity getEntity(Object id)
    {
        return Entitys.get(id);
    }
    public IComponent getEntityComponent(Object id,Class<? extends IComponent>componentClass)
    {
        Entity entity = Entitys.get(id);
        return entity.getComponent(componentClass);
    }
    public void update(float dt)
    {
        Stream.of(Entitys).forEach((entry)->{entry.getValue().update(dt);});
    }




}
