package com.shooterman.game.MainClass;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.shooterman.game.Component.AnimationComponent;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.Entity.EntityManager;
import com.shooterman.game.Component.Entity.EntitySpawner;
import com.shooterman.game.Component.HealthBarComponent;
import com.shooterman.game.Component.InputComponent;
import com.shooterman.game.Component.PhysicComponent;
import com.shooterman.game.Component.RenderComponent;
import com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController;
import com.shooterman.game.Things.Parallaxutil;
import com.shooterman.game.Things.TouchpadInput;

import static com.shooterman.game.MainClass.ShooterMain.*;
import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.*;
import static com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager.*;

public class PlayScreen implements Screen {


    private TouchpadInput touchpad;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Parallaxutil parallaxutil;
    private EntityManager em;
    private SpriteBatch sb;
    private Body playerBody;
    private Body[] barrierBodys;
    private Texture barTexture;
    private Texture playerPositionInWorld;


    public PlayScreen(SpriteBatch sb) {


        this.sb = new SpriteBatch();
        this.touchpad = new TouchpadInput(sb);
        parallaxutil = new Parallaxutil();


        box2DDebugRenderer = new Box2DDebugRenderer();

        em = new EntityManager();
        PhysicsController.initWorld(em);
        EntitySpawner entitySpawner = new EntitySpawner(sb, em);
        barrierBodys = new Body[2];
        barrierBodys[0] = ((PhysicComponent) entitySpawner.makeBarriers(camera.position.x, HEIGHT).getComponent(PhysicComponent.class)).getBody();
        barrierBodys[1] = ((PhysicComponent) entitySpawner.makeBarriers(camera.position.x, 0).getComponent(PhysicComponent.class)).getBody();

        playerBody = ((PhysicComponent) entitySpawner.makePlayer().getComponent(PhysicComponent.class)).getBody();
        entitySpawner.makeDragon();
        entitySpawner.makeDragon();
        entitySpawner.makeDragon();
        entitySpawner.makeDragon();
        entitySpawner.makeDragon();
        barTexture = AssetsManager.Manager.getManager().get("environment/Bar.png");
        playerPositionInWorld = AssetsManager.Manager.getManager().get("environment/playerPositionpaddle.png");




    }

    @Override
    public void render(float delta) {
        update(delta);
        camera.update();
        Bx2dCamera.update();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        //sb.setColor(16/255f,78/255f,139/255f,0.8f);
        parallaxutil.render(sb);
        sb.draw(barTexture,camera.position.x - (WIDTH/2) + barTexture.getWidth()/2,500);
        float x = (playerBody.getPosition().x*100f) * barTexture.getWidth() / (WORLD_WIDTH);
        System.out.println(x);

        sb.draw(playerPositionInWorld,((camera.position.x - (WIDTH/2))+barTexture.getWidth()) - x/barTexture.getWidth(),510);
        sb.end();

        box2DDebugRenderer.render(PhysicsController.world, Bx2dCamera.combined);
        em.update(delta);
        touchpad.draw();


    }

    @Override
    public void resize(int width, int height) {
        //      stViewport.update(width, height);
        touchpad.getViewport().update(width, height);


    }


    private void update(float dt) {
        InputComponent inputComponent = (InputComponent) em.getEntityComponent("player", InputComponent.class);
        inputComponent.sendMessage(new float[]{touchpad.getTouchpad().getKnobPercentX() * 3f, touchpad.getTouchpad().getKnobPercentY() * 3f});
        Body p = ((PhysicComponent) em.getEntityComponent("player", PhysicComponent.class)).getBody();

        camera.position.x = p.getPosition().x * PPM;
        Bx2dCamera.position.x = p.getPosition().x;
        PhysicsController.update(dt);
        parallaxutil.update(p.getLinearVelocity().x, touchpad.getTouchpad().getKnobPercentX());
        barrierBodys[0].setLinearVelocity(playerBody.getLinearVelocity().x, 0);
        barrierBodys[1].setLinearVelocity(playerBody.getLinearVelocity().x, 0);

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
