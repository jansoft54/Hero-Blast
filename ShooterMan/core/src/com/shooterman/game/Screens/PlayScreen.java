package com.shooterman.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.shooterman.game.MainClass.ShooterMain;
import com.shooterman.game.Things.Parallaxutil;
import com.shooterman.game.Things.TouchpadInput;
import com.shooterman.game.Ui.UI;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController.world;
import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.*;

public class PlayScreen implements Screen {


    private OrthographicCamera bx2dcamera;//
    private OrthographicCamera gamecam;

    private SpriteBatch sb;
    private TouchpadInput touchpad;
    private StretchViewport stViewport;


    private Box2DDebugRenderer box2DDebugRenderer;
    private Parallaxutil parallaxutil;




    public PlayScreen(ShooterMain sm, OrthographicCamera camera) {

        this.gamecam = camera;
        this.bx2dcamera = new OrthographicCamera();
        this.sb = new SpriteBatch();
        Stage stage = new Stage(new StretchViewport(ShooterMain.WIDTH, ShooterMain.HEIGHT));
        this.touchpad = new TouchpadInput(stage, this);


        stViewport = new StretchViewport(ShooterMain.WIDTH, ShooterMain.HEIGHT, gamecam);
        gamecam.setToOrtho(false, ShooterMain.WIDTH, ShooterMain.HEIGHT);
        stViewport.apply();

        bx2dcamera.setToOrtho(false, ShooterMain.WIDTH / PPM, ShooterMain.HEIGHT / PPM);
        parallaxutil = new Parallaxutil();

        UI ui = new UI(this);
        ui.addActor(stage);


        box2DDebugRenderer = new Box2DDebugRenderer();

    }

    @Override
    public void render(float delta) {
        update(delta);
        gamecam.update();
        sb.setProjectionMatrix(gamecam.combined);
        sb.begin();

        parallaxutil.render(sb);

        sb.end();

        touchpad.draw();
        box2DDebugRenderer.render(world, bx2dcamera.combined);

    }

    @Override
    public void resize(int width, int height) {
        stViewport.update(width, height);
    }



    private void update(float dt) {


    }



    @Override
    public void show() {
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
