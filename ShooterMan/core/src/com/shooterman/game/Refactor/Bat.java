package com.shooterman.game.Refactor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.shooterman.game.Observer.Observable;
import com.shooterman.game.Observer.Observer;
import com.shooterman.game.Screens.PlayScreen;
import com.shooterman.game.Things.SingleAnimation;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;



public class  Bat implements GameObj,Observer {
    PlayScreen playScreen;
    World world;
    SingleAnimation singleAnimation;
    float time;
    Body body;
    Fixture fixture;
    int x;
    int y;
    Vector2 vec2;


    public Bat(PlayScreen ps, World world, String File) {
        playScreen = ps;
        this.world = world;
        singleAnimation = new SingleAnimation(File, 1 / 8f);
        vec2 = new Vector2();
        x = 500;
        y = 300;
        setupBox2d();


    }


    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public void update(float dt) {
        body.setLinearVelocity(-0.2f, 0);
        time += dt;

    }

    @Override
    public void render(float dt, SpriteBatch sb) {
        update(dt);

        calculatePosition(this);
        sb.draw(singleAnimation.getFrame(time), (body.getPosition().x * PPM) - 50, body.getPosition().y * PPM - 40);

    }

    private void calculatePosition(Bat t) {

    }

    @Override
    public void setupBox2d() {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PPM, y / PPM);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(50 / PPM, 30 / PPM);
        fixtureDef.shape = polygonShape;

        /*
        fixtureDef.filter.categoryBits = Vars.TINYMONTER_BIT;
        fixtureDef.filter.maskBits = Vars.PLAYER_BIT;
        */
        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);


        fixture.setUserData(this);
        body.setGravityScale(0);
    }

    @Override
    public <T> void tellEvent(Observable ob, T[] d) {

    }
}
