package com.shooterman.game.Things;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager;
import com.shooterman.game.MainClass.ShooterMain;


public class Parallaxutil {


    private Sprite layer1L;
    private Sprite layer1R;
    private Sprite layer2L;
    private Sprite layer2R;
    private Sprite layer3L;
    private Sprite layer3R;



    public Parallaxutil() {


        layer1L = new Sprite(AssetsManager.Manager.getManager().get("Parallax/layer-1.png", Texture.class));
        layer1R = new Sprite(AssetsManager.Manager.getManager().get("Parallax/layer-1.png", Texture.class));
        layer2L = new Sprite(AssetsManager.Manager.getManager().get("Parallax/layer-2.png", Texture.class));
        layer2R = new Sprite(AssetsManager.Manager.getManager().get("Parallax/layer-2.png", Texture.class));
        layer3L = new Sprite(AssetsManager.Manager.getManager().get("Parallax/layer-4.png", Texture.class));
        layer3R = new Sprite(AssetsManager.Manager.getManager().get("Parallax/layer-4.png", Texture.class));

        layer1L.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        layer1R.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        layer2L.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        layer2R.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        layer3L.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        layer3R.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        layer3L.flip(false, true);
        layer3R.flip(false, true);


        layer1R.setPosition(layer1L.getWidth(), 0);
        layer2R.setPosition(layer2L.getWidth(), 0);
        layer3R.setPosition(layer3L.getWidth(), 0);

    }

    public void render(SpriteBatch sb) {

        sb.draw(layer1L.getTexture(), layer1L.getX(), 0);
        sb.draw(layer1R.getTexture(), layer1R.getX(), 0);
        sb.draw(layer2L.getTexture(), layer2L.getX(), 0);
        sb.draw(layer2R.getTexture(), layer2R.getX(), 0);
        sb.draw(layer3L.getTexture(), layer3L.getX(), -10);
        sb.draw(layer3R.getTexture(), layer3R.getX(), -10);


    }


    public void update(float velocityX, float KnopPercentX) {

        if (Gdx.graphics.getDeltaTime() > 0.0f) {
            layer1L.translate((-0.2f * KnopPercentX / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
            layer1R.translate((-0.2f * KnopPercentX / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
            layer2L.translate((-1.25f * KnopPercentX / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
            layer2R.translate((-1.25f * KnopPercentX / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
        }

        long cameraOffsetXL = (long) (ShooterMain.camera.position.x - ShooterMain.WIDTH / 2);
        long cameraOffsetXR = (long) (ShooterMain.camera.position.x + ShooterMain.WIDTH / 2);

        if (velocityX > 0) {
            if (cameraOffsetXR > layer1L.getX() + layer1L.getWidth() && layer1R.getX() < layer1L.getX())
                layer1R.setPosition(layer1L.getX() + layer1L.getWidth(), 0);
            else if (cameraOffsetXR > layer1R.getX() + layer1R.getWidth() && layer1L.getX() < layer1R.getX())
                layer1L.setPosition(layer1R.getX() + layer1R.getWidth(), 0);

            if (cameraOffsetXR > layer2L.getX() + layer2L.getWidth() && layer2R.getX() < layer2L.getX())
                layer2R.setPosition(layer2L.getX() + layer2L.getWidth(), 0);
            else if (cameraOffsetXR > layer2R.getX() + layer2R.getWidth() && layer2L.getX() < layer2R.getX())
                layer2L.setPosition(layer2R.getX() + layer2R.getWidth(), 0);

            if (cameraOffsetXR > layer3L.getX() + layer3L.getWidth() && layer3R.getX() < layer3L.getX())
                layer3R.setPosition(layer3L.getX() + layer3L.getWidth(), 0);
            else if (cameraOffsetXR > layer3R.getX() + layer3R.getWidth() && layer3L.getX() < layer3R.getX())
                layer3L.setPosition(layer3R.getX() + layer3R.getWidth(), 0);

        } else if (velocityX < 0) {

            if (cameraOffsetXL < layer1R.getX() && layer1L.getX() > layer1R.getX())
                layer1L.setPosition(layer1R.getX() - layer1L.getWidth(), 0);
            else if (cameraOffsetXL < layer1L.getX() && layer1R.getX() > layer1L.getX())
                layer1R.setPosition(layer1L.getX() - layer1R.getWidth(), 0);

            if (cameraOffsetXL < layer2R.getX() && layer2L.getX() > layer2R.getX())
                layer2L.setPosition(layer2R.getX() - layer2L.getWidth(), 0);
            else if (cameraOffsetXL < layer2L.getX() && layer2R.getX() > layer2L.getX())
                layer2R.setPosition(layer2L.getX() - layer2R.getWidth(), 0);

            if (cameraOffsetXL < layer3R.getX() && layer3L.getX() > layer3R.getX())
                layer3L.setPosition(layer3R.getX() - layer3L.getWidth(), 0);
            else if (cameraOffsetXL < layer3L.getX() && layer3R.getX() > layer3L.getX())
                layer3R.setPosition(layer3L.getX() - layer3R.getWidth(), 0);

        }

    }

    public void dispose() {

    }


}
