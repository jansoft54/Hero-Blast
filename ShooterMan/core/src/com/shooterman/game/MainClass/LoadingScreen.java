package com.shooterman.game.MainClass;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager;


public class LoadingScreen implements Screen {



    public LoadingScreen() {

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
          ShooterMain.shootermain.setScreen(new PlayScreen( camera));
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
