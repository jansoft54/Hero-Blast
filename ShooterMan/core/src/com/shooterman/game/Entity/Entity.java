package com.shooterman.game.Entity;
import com.annimon.stream.Stream;
import com.shooterman.game.Component.IComponent.IComponent;
import java.util.HashMap;


public class Entity {
    private HashMap<Class<? extends IComponent>,IComponent> Components;
    private Object id;
    Entity(Object id){
        Components = new HashMap<>();
        this.id = id;
    }
    void addComponent(Class<? extends IComponent> componentClass,IComponent component) {
            Components.put(componentClass,component);
    }
    IComponent getComponent(Class<? extends IComponent> component)
    {
        return Components.get(component);
    }
    public Object getId()
    {
        return id;
    }

    public void update(float dt) {
        Stream.of(Components).forEach(data -> data.getValue().update(dt));
    }
}
