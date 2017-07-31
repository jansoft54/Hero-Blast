package com.shooterman.game.GameObject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public interface GameObj {

    Body getBody();
    void update(float dt);
    void render(float dt, SpriteBatch sb);
    void setupBox2d();


}
