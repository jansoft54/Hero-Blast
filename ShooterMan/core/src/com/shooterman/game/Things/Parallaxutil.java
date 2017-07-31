package com.shooterman.game.Things;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by master on 15.10.2016.
 */

public class Parallaxutil {

    private Sprite layer1;
    private Sprite layer2;
    private Sprite layer3;
    private Sprite layer4;
    private Sprite layer11;
    private Sprite layer22;
    private Sprite layer33;
    private Sprite layer44;
    private float scaleX;

    public Parallaxutil() {
        layer1 = new Sprite(new Texture("Parallax/layer-1.png"));
        layer2 = new Sprite(new Texture("Parallax/layer-2.png"));
        layer3 = new Sprite(new Texture("Parallax/layer-3.png"));
        layer4 = new Sprite(new Texture("Parallax/layer-4.png"));
        layer11 = new Sprite(new Texture("Parallax/layer-1.png"));
        layer22 = new Sprite(new Texture("Parallax/layer-2.png"));
        layer33 = new Sprite(new Texture("Parallax/layer-3.png"));
        layer44 = new Sprite(new Texture("Parallax/layer-4.png"));

        layer1.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        layer2.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        layer3.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        layer4.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        layer11.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        layer22.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        layer33.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        layer44.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        layer11.setPosition(layer1.getWidth(), 0);
        layer22.setPosition(layer2.getWidth(), 0);
        layer33.setPosition(layer3.getWidth(), 0);
        layer44.setPosition(layer4.getWidth(), 0);
    }

    public void render(SpriteBatch sb) {
        update();

            sb.draw(layer1.getTexture(), layer1.getX(), 0);
            sb.draw(layer11.getTexture(), layer11.getX(), 0);
            sb.draw(layer2.getTexture(), layer2.getX(), 0);
            sb.draw(layer22.getTexture(), layer22.getX(), 0);
            sb.draw(layer3.getTexture(), layer3.getX(), 0);
            sb.draw(layer33.getTexture(), layer33.getX(), 0);
            sb.draw(layer4.getTexture(), layer4.getX(), 0);
            sb.draw(layer44.getTexture(), layer44.getX(), 0);


    }


    public void update() {
        float backVelamount = 2f;
        if (Gdx.graphics.getDeltaTime() > 0.0f) {
            layer1.translate((-0.2f / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
            layer2.translate((-0.4f / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
            layer3.translate((-1f / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
            layer4.translate((-2f / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
            layer11.translate((-0.2f / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
            layer22.translate((-0.4f / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
            layer33.translate((-1f / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
            layer44.translate((-2f / Gdx.graphics.getDeltaTime()) * Gdx.graphics.getDeltaTime(), 0);
        }

        if (layer1.getX() + layer1.getWidth() <= 0) {
            layer1.setPosition((layer1.getX() + layer1.getWidth() * 2) , 0);
        }
        if (layer11.getX() + layer11.getWidth() <= 0) {
            layer11.setPosition((layer11.getX() + layer11.getWidth() * 2), 0);
        }


        if (layer2.getX() + layer2.getWidth() <= 0) {
            layer2.setPosition(layer2.getX() + layer2.getWidth() * 2, 0);
        }
        if (layer22.getX() + layer22.getWidth() <= 0) {
            layer22.setPosition(layer22.getX() + layer22.getWidth() * 2, 0);
        }


        if (layer3.getX() + layer3.getWidth() <= 0) {
            layer3.setPosition(layer3.getX() + layer3.getWidth() * 2, 0);
        }
        if (layer33.getX() + layer33.getWidth() <= 0) {
            layer33.setPosition(layer33.getX() + layer33.getWidth() * 2, 0);
        }


        if (layer4.getX() + layer4.getWidth() <= 0) {
            layer4.setPosition(layer4.getX() + layer4.getWidth() * 2, 0);
        }
        if (layer44.getX() + layer44.getWidth() <= 0) {
            layer44.setPosition(layer44.getX() + layer44.getWidth() * 2, 0);
        }


    }
    public  void dispose(){

    }


}
