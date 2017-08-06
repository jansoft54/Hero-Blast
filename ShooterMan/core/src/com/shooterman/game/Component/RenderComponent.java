package com.shooterman.game.Component;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;


public final class RenderComponent implements IComponent {

    private TextureRegion currentFrame;
    private SpriteBatch spriteBatch;
    private PhysicComponent physicComponent;
    private Entity myEntity;


    public RenderComponent(Texture renderTexture, SpriteBatch spriteBatch, Entity myEntity) {
        this(spriteBatch, myEntity);
        this.currentFrame = new TextureRegion(renderTexture);
    }

    public RenderComponent(SpriteBatch spriteBatch, Entity myEntity) {
        this.spriteBatch = spriteBatch;
        this.myEntity = myEntity;
        this.physicComponent = (PhysicComponent) myEntity.getComponent(PhysicComponent.class);
    }

    @Override
    public void update(float delta) {
        render();
    }

    private void render() {
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, physicComponent.getPosition().x, physicComponent.getPosition().y);
        spriteBatch.end();
    }

    void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;

    }


    @Override
    public void clearData() {

    }
}
