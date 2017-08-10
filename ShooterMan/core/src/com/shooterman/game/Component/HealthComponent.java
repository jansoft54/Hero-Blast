package com.shooterman.game.Component;

import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;


public class HealthComponent implements IComponent {
    private float currenthealth;
    private final float fullhealth;
    private Entity myEnity;

    public HealthComponent(Entity myEnity,float fullhealth) {
        this.fullhealth = fullhealth;
        this.currenthealth = fullhealth;
        this.myEnity = myEnity;
    }

    @Override
    public void update(float delta) {

    }
    public void takeDamage(float damage){
        currenthealth -= damage;
        if(myEnity.hasComponent(HealthBarComponent.class))
            ((HealthBarComponent)myEnity.getComponent(HealthBarComponent.class,true)).sendMessage(currenthealth);
    }


    @Override
    public void clearData() {

    }

    float getCurrenthealth() {
        return currenthealth;
    }
    float getFullhealth() {
        return currenthealth;
    }
}
