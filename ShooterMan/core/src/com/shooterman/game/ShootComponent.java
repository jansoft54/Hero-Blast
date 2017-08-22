package com.shooterman.game;


import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.Entity.EntityManager;
import com.shooterman.game.Component.Entity.EntitySpawner;
import com.shooterman.game.Component.IComponent.IComponent;
import com.shooterman.game.Component.PhysicComponent;



public class ShootComponent implements IComponent {
    private Entity myEntity;
    private PhysicComponent physicComponent;
    private EntitySpawner entitySpawner;
    float time;



    public ShootComponent(Entity myEntity, EntitySpawner entitySpawner) {
        this.myEntity = myEntity;
        this.physicComponent = ((PhysicComponent)myEntity.getComponent(PhysicComponent.class));

        this.entitySpawner = entitySpawner;

    }

    public void shoot(){
        entitySpawner.makeShot(physicComponent.getBody());

    }

    @Override
    public void update(float delta) {
        time+=delta;
        if(time>=1)
        {
           // shoot();
            time=0;
        }

    }

    @Override
    public void clearData() {

    }
}
