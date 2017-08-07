package com.shooterman.game.Component;

import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;


public class HealthComponent implements IComponent {
    private float health;
    private Entity myEnity;

    public HealthComponent(Entity myEnity,float health) {
        this.health = health;
        this.myEnity = myEnity;
    }

    @Override
    public void update(float delta) {

    }
    public void takeDamage(float damage){
        health-= damage;
    }


    @Override
    public void clearData() {

    }

    float getHealth() {
        return health;
    }
}
