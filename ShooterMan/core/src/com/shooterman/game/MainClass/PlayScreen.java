package com.shooterman.game.MainClass;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
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


    private OrthographicCamera bx2dcamera;//
    private TouchpadInput touchpad;
    private StretchViewport stViewport;

    private Box2DDebugRenderer box2DDebugRenderer;
    private Parallaxutil parallaxutil;
    private Stage stage;
    private EntityManager em;
    private SpriteBatch sb;
    World world;


    public PlayScreen(SpriteBatch sb) {

        this.bx2dcamera = new OrthographicCamera();
        this.sb = new SpriteBatch();
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT, camera));
        this.touchpad = new TouchpadInput(stage, this);
        world = new World(new Vector2(0,-4.81f),true);



        stViewport = new StretchViewport( WIDTH, HEIGHT, camera);

        stViewport.apply();
        System.out.println(WIDTH +" " + PPM +"="+WIDTH/PPM);
        System.out.println(HEIGHT/PPM);
        bx2dcamera.setToOrtho(false, WIDTH/PPM,HEIGHT/PPM);

        parallaxutil = new Parallaxutil();

        UI ui = new UI(this);
        ui.addActor(stage);
       // PhysicsController.Companion.initWorld();

        box2DDebugRenderer = new Box2DDebugRenderer();

        em = new EntityManager();
        Entity entity = em.makeEntity("player");
        em.addEntityToWorld(entity);
        PolygonShape polygonShape =  new PolygonShape();
        polygonShape.setAsBox(45/PPM,32/PPM);

        PhysicComponent physicComponent = new PhysicComponent(new Vector2(((WIDTH/2)-45)/PPM,((HEIGHT/2)-32)/PPM), entity,BodyDef.BodyType.DynamicBody,world);
        physicComponent.addFixture(polygonShape,0.2f,0f,0f,false);

        em.addEntityComponent(entity.getId(), physicComponent);
        em.addEntityComponent(entity.getId(), new RenderComponent(sb,entity));
        em.addEntityComponent(entity.getId(), new AnimationComponent(entity).addAnimation("playerFly", 1 / 5f, Animation.PlayMode.LOOP, ((TextureAtlas)Manager.getManager().get("Atlases/Playeranim/player.atlas")).getRegions()));
        em.addEntityComponent(entity.getId(), new InputComponent(entity));

    }

    @Override
    public void render(float delta) {

        camera.update();
        bx2dcamera.update();
        sb.setProjectionMatrix(camera.combined);

        sb.begin();
      //  parallaxutil.render(sb);
        sb.end();
        box2DDebugRenderer.render(world,bx2dcamera.combined);
        em.update(delta);

        touchpad.draw();
        update(delta);

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
        world.step(dt,6,2);



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
