package com.shooterman.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager;
import com.shooterman.game.Refactor.CoinCreator;
import com.shooterman.game.Refactor.Bat;
import com.shooterman.game.Refactor.Dragon;
import com.shooterman.game.Refactor.MonsterShot;
import com.shooterman.game.Refactor.Spikes;
import com.shooterman.game.Refactor.Player;
import com.shooterman.game.Refactor.Shot;
import com.shooterman.game.MainClass.ShooterMain;
import com.shooterman.game.TextureHolder;
import com.shooterman.game.Things.Parallaxutil;
import com.shooterman.game.Things.SingleAnimation;
import com.shooterman.game.Things.TouchpadInput;
import com.shooterman.game.Ui.UI;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController.world;
import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.*;

public class PlayScreen implements Screen {


    private OrthographicCamera bx2dcamera;//
    private OrthographicCamera gamecam;

    private SpriteBatch sb;
    private Stage stage;//
    private TouchpadInput touchpad;
    private StretchViewport stViewport;

    private SingleAnimation animation;//


    private Box2DDebugRenderer box2DDebugRenderer;
    private Parallaxutil parallaxutil;
    public boolean isactive = true;//



    public PlayScreen(ShooterMain sm, OrthographicCamera camera) {

        this.gamecam = camera;
        this.bx2dcamera = new OrthographicCamera();
        this.sb = new SpriteBatch();
        this.stage = new Stage(new StretchViewport(ShooterMain.WIDTH, ShooterMain.HEIGHT));
        this.touchpad = new TouchpadInput(stage, this);
        this.animation = new SingleAnimation(AssetsManager.Manager.getManager().get("Atlases/buttonatlas/hand.atlas"), 1 / 3f);


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

    private void drawTutorial() {


    }

    private void update(float dt) {



    }

    private void handleInput() {


    }


    private void drawAnimations(SpriteBatch sb) {
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
