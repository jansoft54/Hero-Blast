package com.shooterman.game.MainClass;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShooterMain extends Game {
    public static final float WIDTH = 800;
    public static final float HEIGHT = 450;
    public static ShooterMain shootermain;
    public static OrthographicCamera camera;

    public ShooterMain() {
        shootermain = this;
    }

    @Override
    public void create() {
       camera = new OrthographicCamera();
        camera.setToOrtho(false,WIDTH,HEIGHT);
        setScreen(new LoadingScreen());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        super.render();
    }


}
