package com.shooterman.game.Component;

import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;


public class EnemyComponent implements IComponent {

   private PhysicComponent physicComponent;
   private float time = (float) Math.random();

    public EnemyComponent(Entity entity) {
        physicComponent = (PhysicComponent) entity.getComponent(PhysicComponent.class);
    }

    @Override
    public void update(float delta) {
        time+=delta;
        physicComponent.setLinearVelocity(-1, (float) Math.sin(Math.PI+time*5));

    }

    @Override
    public void clearData() {

    }
}
