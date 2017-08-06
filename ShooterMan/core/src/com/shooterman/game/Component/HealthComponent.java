package com.shooterman.game.Component;

import com.shooterman.game.Component.IComponent.IComponent;



public class HealthComponent implements IComponent {
    private float health;

    public HealthComponent(float health) {
        this.health = health;
    }

    @Override
    public void update(float delta) {

    }


    @Override
    public void clearData() {

    }

    public float getHealth() {
        return health;
    }
}
