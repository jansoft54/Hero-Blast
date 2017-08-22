package com.shooterman.game.Component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;
import com.shooterman.game.MainClass.ShooterMain;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;

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
        if (currentFrame != null) render();
    }

    private void render() {
        spriteBatch.setProjectionMatrix(ShooterMain.camera.combined);
       /// spriteBatch.setColor(16/255f,78/255f,139/255f,0.8f);
        spriteBatch.begin();
        float hRegionW = currentFrame.getRegionWidth() / 2;
        float hRegionH = currentFrame.getRegionHeight() / 2;
        spriteBatch.draw(currentFrame, physicComponent.getPositionX() - hRegionW, physicComponent.getPositionY() - hRegionH);
        spriteBatch.end();
    }

    void setCurrentFrame(TextureRegion currentFrame) {
        this.currentFrame = currentFrame;

    }


    @Override
    public void clearData() {

    }
}
