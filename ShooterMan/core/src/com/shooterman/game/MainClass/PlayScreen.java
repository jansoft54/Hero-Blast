package com.shooterman.game.MainClass;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.shooterman.game.Component.AnimationComponent;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.Entity.EntityManager;
import com.shooterman.game.Component.InputComponent;
import com.shooterman.game.Component.PhysicComponent;
import com.shooterman.game.Component.RenderComponent;

import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController;
import com.shooterman.game.Things.Parallaxutil;
import com.shooterman.game.Things.TouchpadInput;
import com.shooterman.game.Ui.UI;

import static com.shooterman.game.MainClass.ShooterMain.*;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.*;
import static com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager.*;

public class PlayScreen implements Screen {


    private TouchpadInput touchpad;
    private StretchViewport stViewport;

    private Box2DDebugRenderer box2DDebugRenderer;
    private Parallaxutil parallaxutil;
    private Stage stage;
    private EntityManager em;
    private SpriteBatch sb;


    public PlayScreen(SpriteBatch sb) {


        this.sb = new SpriteBatch();
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT, camera));
        this.touchpad = new TouchpadInput(stage, this);

        stViewport = new StretchViewport(WIDTH, HEIGHT, camera);
        stViewport.apply();
        parallaxutil = new Parallaxutil();

        UI ui = new UI(this);
        ui.addActor(stage);
        PhysicsController.initWorld();

        box2DDebugRenderer = new Box2DDebugRenderer();

        em = new EntityManager();
        Entity entity = em.makeEntity("player");
        em.addEntityToWorld(entity);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(45 / PPM, 32 / PPM);

        PhysicComponent physicComponent = new PhysicComponent(new Vector2(((WIDTH / 2) - 45) / PPM, ((HEIGHT / 2) - 32) / PPM), entity, BodyDef.BodyType.DynamicBody, PhysicsController.world);
        physicComponent.addFixture(polygonShape, 0.2f, 0f, 0f, false);

        em.addEntityComponent(entity.getId(), physicComponent);
        em.addEntityComponent(entity.getId(), new RenderComponent(sb, entity));
        em.addEntityComponent(entity.getId(), new AnimationComponent(entity).addAnimation("playerFly", 1 / 5f, Animation.PlayMode.LOOP, ((TextureAtlas) Manager.getManager().get("Atlases/Playeranim/player.atlas")).getRegions()));
        em.addEntityComponent(entity.getId(), new InputComponent(entity));


        BodyDef bdef = new BodyDef();
        bdef.position.set(160 / PPM, 120 / PPM);
        bdef.type = BodyDef.BodyType.StaticBody;
        Body body = PhysicsController.world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50 / PPM, 5 / PPM);
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        body.createFixture(fdef);

    }

    @Override
    public void render(float delta) {
        update(delta);
        camera.update();
        Bx2dCamera.update();

        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        parallaxutil.render(sb);
        sb.end();

        box2DDebugRenderer.render(PhysicsController.world, Bx2dCamera.combined);
        em.update(delta);
        touchpad.draw();


    }

    @Override
    public void resize(int width, int height) {
        stViewport.update(width, height);
        stage.getViewport().update(width, height);

    }


    private void update(float dt) {
        InputComponent inputComponent = (InputComponent) em.getEntityComponent("player", InputComponent.class, true);
        inputComponent.sendMessage(new float[]{touchpad.getTouchpad().getKnobPercentX() * 5f, touchpad.getTouchpad().getKnobPercentY() * 5f});
        PhysicsController.update(dt);


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
