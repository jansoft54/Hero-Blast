package com.shooterman.game.MainClass;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager;


public class LoadingScreen implements Screen {

    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    LoadingScreen() {
        AssetsManager.Manager.load();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, ShooterMain.WIDTH, ShooterMain.HEIGHT);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        if (!AssetsManager.Manager.getManager().update()) {

            float progress = AssetsManager.Manager.getManager().getProgress();

            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(37 / 255f, 79 / 255f, 58 / 255f, 1);
            shapeRenderer.rect(0, ShooterMain.HEIGHT / 2, ShooterMain.WIDTH * progress, ShooterMain.HEIGHT * 0.01f);
            shapeRenderer.end();
            System.out.println(progress * 100);

        } else
            ShooterMain.shootermain.setScreen(new PlayScreen(new SpriteBatch(), camera));
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
