package com.shooterman.game.MainClass;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.shooterman.game.Component.AnimationComponent;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.Entity.EntityManager;
import com.shooterman.game.Component.InputComponent;
import com.shooterman.game.Component.PhysicComponent;
import com.shooterman.game.Component.RenderComponent;
import com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager;
import com.shooterman.game.Things.Parallaxutil;
import com.shooterman.game.Things.TouchpadInput;
import com.shooterman.game.Ui.UI;
import static com.shooterman.game.MainClass.ShooterMain.*;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController.world;
import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.*;

public class PlayScreen implements Screen {


    private OrthographicCamera bx2dcamera;//
    private OrthographicCamera gamsecam;


    private TouchpadInput touchpad;
    private StretchViewport stViewport;

    private Box2DDebugRenderer box2DDebugRenderer;
    private Parallaxutil parallaxutil;
    private Stage stage;
    private EntityManager em;
    private SpriteBatch sb;

    public PlayScreen(SpriteBatch sb,OrthographicCamera camera) {

        this.bx2dcamera = new OrthographicCamera();
        this.sb = new SpriteBatch();
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT, camera));
        this.touchpad = new TouchpadInput(stage, this);


        stViewport = new StretchViewport( WIDTH, HEIGHT, camera);
        camera.setToOrtho(false, WIDTH, HEIGHT);
        stViewport.apply();
        bx2dcamera.setToOrtho(false, WIDTH / PPM, HEIGHT / PPM);
        parallaxutil = new Parallaxutil();

        UI ui = new UI(this);
        ui.addActor(stage);


        box2DDebugRenderer = new Box2DDebugRenderer();

        em = new EntityManager();
        Entity entity = em.makeEntity("player");
        em.addEntityToWorld(entity);
        em.addEntityComponent(entity.getId(), new PhysicComponent(new Vector2(100, 100), entity));
        em.addEntityComponent(entity.getId(), new RenderComponent(sb,entity));
        em.addEntityComponent(entity.getId(), new AnimationComponent(entity)
                .addAnimation("playerFly", 1 / 5f, Animation.PlayMode.LOOP, ((TextureAtlas) AssetsManager.Manager.getManager().get("Atlases/Playeranim/player.atlas")).getRegions()));
        em.addEntityComponent(entity.getId(), new InputComponent(entity));

    }

    @Override
    public void render(float delta) {
        update(delta);
        camera.update();
        sb.setProjectionMatrix(camera.combined);

        sb.begin();
        parallaxutil.render(sb);
        sb.end();

        em.update(delta);

        touchpad.draw();
        box2DDebugRenderer.render(world, bx2dcamera.combined);

    }

    @Override
    public void resize(int width, int height) {
        System.out.print("Width " + width + " height " + height);
        stViewport.update(width, height);
        stage.getViewport().update(width, height);

    }


    private void update(float dt) {
        InputComponent inputComponent = (InputComponent) em.getEntityComponent("player", InputComponent.class);
        inputComponent.sendMessage(new float[]{touchpad.getTouchpad().getKnobPercentX() * 10, touchpad.getTouchpad().getKnobPercentY() * 10});

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
