package com.shooterman.game.Component;

import com.shooterman.game.Component.IComponent.IComponent;


public class HealthComponent implements IComponent {
    float currentHealth;
    final float fullHealth;


    HealthComponent(float fullhealth) {
        this.fullHealth = fullhealth;
        this.currentHealth = fullhealth;
    }

    @Override
    public void update(float delta) {

    }

    public void takeDamage(float damage) {
        currentHealth -= damage;

    }


    @Override
    public void clearData() {

    }

}
