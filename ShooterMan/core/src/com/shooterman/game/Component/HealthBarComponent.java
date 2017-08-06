package com.shooterman.game.Component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;
import com.shooterman.game.Component.IComponent.IMessage;


public class HealthBarComponent implements IComponent, IMessage {

    private Entity myEnity;
    private ShapeRenderer shapeRenderer;
    private HealthComponent healthComponent;
    private PhysicComponent physicComponent;
    private float fullHealth, entityHealth, red, green, w, h;

    public HealthBarComponent(Entity myEnity, float w, float h, float fullHealth) {
        this.w = w;
        this.h = h;
        this.green = 255;
        this.red = 0;
        this.myEnity = myEnity;
        this.fullHealth = fullHealth;
        this.shapeRenderer = new ShapeRenderer();
        this.healthComponent = (HealthComponent) myEnity.getComponent(HealthComponent.class);
        this.physicComponent = (PhysicComponent) myEnity.getComponent(PhysicComponent.class);
        this.entityHealth = healthComponent.getHealth();
    }

    @Override
    public void update(float delta) {
        render();
    }

    private void render() {
        shapeRenderer.setProjectionMatrix();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(physicComponent.getPosition().x, physicComponent.getPosition().y, w, h);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(physicComponent.getPosition().x, physicComponent.getPosition().y, w, h);
        shapeRenderer.setColor(red / 255f, green / 255f, 0, 1);
        shapeRenderer.rect(physicComponent.getPosition().x, physicComponent.getPosition().y, w, h);
    }

    @Override
    public <T> void sendMessage(T data) {
        if (data instanceof Integer) {
            entityHealth -= (Integer) data;
            entityHealth = entityHealth < 0 ? 0 : entityHealth;

            green = entityHealth > 0 ? fullHealth / (fullHealth / entityHealth) : 0;
            red = 255f - green;
        } else throw new IllegalArgumentException("Health can`t be a " + data.getClass());
    }

    @Override
    public void clearData() {

    }


}
