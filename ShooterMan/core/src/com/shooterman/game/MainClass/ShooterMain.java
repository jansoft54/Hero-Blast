package com.shooterman.game.MainClass;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;


import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;

public class ShooterMain extends Game {
    public static final float WIDTH = 1024;
    public static final float HEIGHT = 576;
    public static final float WORLD_WIDTH = 2000;
    public static ShooterMain shootermain;
    public static OrthographicCamera camera;
    static OrthographicCamera Bx2dCamera;

    public ShooterMain() {
        shootermain = this;
    }

    @Override
    public void create() {
        camera = new OrthographicCamera();
        Bx2dCamera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        Bx2dCamera.setToOrtho(false, WIDTH / PPM, HEIGHT / PPM);
        setScreen(new LoadingScreen());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        super.render();
    }


}
