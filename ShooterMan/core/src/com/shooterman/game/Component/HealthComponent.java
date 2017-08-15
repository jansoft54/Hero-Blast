package com.shooterman.game.Component;

import com.shooterman.game.Component.IComponent.IComponent;


public class HealthComponent implements IComponent {
     float currentHealth;
     final float fullHealth;


    public HealthComponent(float fullhealth) {
        this.fullHealth = fullhealth;
        this.currentHealth = fullhealth;
    }

    @Override
    public void update(float delta) {

    }
    public void takeDamage(float damage){
        currentHealth -= damage;
        System.out.print("DAMAGE");

    }


    @Override
    public void clearData() {

    }

}
