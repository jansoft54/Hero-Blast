package com.shooterman.game.MainClass;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.shooterman.game.Screens.LoadingScreen;
import com.shooterman.game.Screens.PlayScreen;

public class ShooterMain extends Game {
    public static final int WIDTH=1024;
    public static final int HEIGHT=600;
    @Override
    public void create() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false);
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        super.render();
    }


}
