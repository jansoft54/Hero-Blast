package com.shooterman.game.Component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;
import com.shooterman.game.Component.IComponent.IMessage;

import static com.shooterman.game.MainClass.ShooterMain.*;

public class HealthBarComponent implements IComponent, IMessage {

    private Entity myEnity;
    private ShapeRenderer shapeRenderer;
    private HealthComponent healthComponent;
    private PhysicComponent physicComponent;
    private float fullHealth, entityHealth, red, green, w, h;

    public HealthBarComponent(Entity myEnity, float w, float h) {
        this.w = w;
        this.h = h;
        this.green = 255;
        this.red = 0;
        this.myEnity = myEnity;

        this.shapeRenderer = new ShapeRenderer();
        this.healthComponent = (HealthComponent) myEnity.getComponent(HealthComponent.class, true);
        this.physicComponent = (PhysicComponent) myEnity.getComponent(PhysicComponent.class, true);
        this.entityHealth = healthComponent.getCurrenthealth();
        this.fullHealth = healthComponent.getFullhealth();

    }

    @Override
    public void update(float delta) {
        if (entityHealth < fullHealth && entityHealth > 0)
            render();
    }

    private void render() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(physicComponent.getPositionX() - 1f - 46f, physicComponent.getPositionY() - 1f + 40, w + 2, h + 2);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(physicComponent.getPositionX() - 46f, physicComponent.getPositionY() + 40, w, h);
        shapeRenderer.setColor(red / 255f, green / 255f, 0, 0);
        shapeRenderer.rect(physicComponent.getPositionX() - 46f, physicComponent.getPositionY() + 40, green > 0 ? w * entityHealth / fullHealth : 0, h);
        shapeRenderer.end();
    }

    @Override
    public <T> void sendMessage(T data) {
        if (data instanceof Number) {
            float cur = (Float) data;
            entityHealth = cur > 0 ? cur : 0;
            green = entityHealth > 0 ? fullHealth * entityHealth / fullHealth *1.75f: 0;
            red = 255f - green;
        } else throw new IllegalArgumentException("Health can`t be a " + data.getClass());
    }

    @Override
    public void clearData() {

    }


}
