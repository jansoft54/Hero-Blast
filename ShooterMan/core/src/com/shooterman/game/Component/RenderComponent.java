package com.shooterman.game.Component;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;


public final class RenderComponent implements IComponent {

    private Texture currentFrame;
    private SpriteBatch spriteBatch;
    private PhysicComponent physicComponent;
    private Entity myEntity;


    public RenderComponent(Texture renderTexture, SpriteBatch spriteBatch, Entity myEntity) {
        this.currentFrame = renderTexture;
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
        spriteBatch.draw(currentFrame, physicComponent.getPosition().x,physicComponent.getPosition().y);
        spriteBatch.end();
    }

    public void setCurrentFrame(Texture currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame.getTexture();
    }


    @Override
    public void clearData() {

    }
}
