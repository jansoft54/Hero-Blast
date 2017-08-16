package com.shooterman.game.Component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;
import com.shooterman.game.Component.IComponent.IMessage;

import static com.shooterman.game.MainClass.ShooterMain.*;

public class HealthBarComponent extends HealthComponent implements IComponent {


    private ShapeRenderer shapeRenderer;
    private PhysicComponent physicComponent;
    private float red, green, w, h;

    public HealthBarComponent(Entity myEnity, float fullHealth, float w, float h) {
        super(fullHealth);
        this.w = w;
        this.h = h;
        this.green = 255;
        this.red = 0;
        this.shapeRenderer = new ShapeRenderer();
        this.physicComponent = (PhysicComponent) myEnity.getComponent(PhysicComponent.class, true);
    }


    @Override
    public void takeDamage(float damage) {
        super.takeDamage(damage);
        green = currentHealth > 0 ? fullHealth * currentHealth / fullHealth * 1.75f : 0;
        red = 255f - green;
    }

    @Override
    public void update(float delta) {
        if (currentHealth < fullHealth && currentHealth > 0)
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
        shapeRenderer.rect(physicComponent.getPositionX() - 46f, physicComponent.getPositionY() + 40, green > 0 ? w * currentHealth / fullHealth : 0, h);
        shapeRenderer.end();
    }

    @Override
    public void clearData() {

    }


}
