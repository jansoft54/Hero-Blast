package com.shooterman.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager;
import com.shooterman.game.MainClass.ShooterMain;

public class LoadingScreen implements Screen {

    private ShooterMain shooterMain;

    public LoadingScreen(ShooterMain shooterMain) {
        this.shooterMain = shooterMain;
        AssetsManager.Manager.load();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (!AssetsManager.Manager.getManager().update())
            System.out.println(AssetsManager.Manager.getManager().getProgress() * 100);
        else {
            OrthographicCamera camera = new OrthographicCamera();
            camera.setToOrtho(false);
            shooterMain.setScreen(new PlayScreen(shooterMain, camera));
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}
