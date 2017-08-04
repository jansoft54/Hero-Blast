package com.shooterman.game.Refactor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.BodyFactory;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController;
import com.shooterman.game.Observer.Observable;
import com.shooterman.game.Observer.Observer;
import com.shooterman.game.Screens.PlayScreen;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;

/**
 * Created by master on 22.10.2016.
 */

public class MonsterShot implements Observer,GameObj {

    private SingleAnimation animation;
    private float time;
    private Body body;
    public Dragon dragon;
    private PlayScreen ps;
    private float lifetime = 2.5f;

    public MonsterShot(PlayScreen ps, World world, Dragon dragon, String File) {
        this.ps = ps;
        animation = new SingleAnimation(File,1/10f);
        this.dragon = dragon;
        setupBox2d();
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public <T> void tellEvent(Observable ob, T[] d) {


    }
    @Override
    public void update(float dt) {
        body.setLinearVelocity(-3, 0);
        lifetime -= dt;
        float lifetime = this.lifetime;
        if (lifetime <= 0) {
            ps.removeBodys.add(body);
        }
    }

    @Override
    public void render(float dt, SpriteBatch sb) {
        time += dt;

        update(dt);
        sb.draw(animation.getFrame(time), body.getPosition().x * 100-10, body.getPosition().y * 100-10);

    }



    @Override
    public void setupBox2d() {
          /*Initialize the Box2D relatet properties of the Player. BodyFactory uses the Builder-Pattern*/
        PolygonShape polyShape = new PolygonShape();
        polyShape.setAsBox(16.6f / PPM, 13.3f / PPM);
        body = new BodyFactory
                .Builder(new Vector2(300 / PPM, 200 / PPM), BodyDef.BodyType.DynamicBody, PhysicsController.world, this)
                .setGravityScale(0)
                .setPosition(dragon.getBody().getPosition())
                .addFixture(polyShape, 0.2f, 0.01f, 0, true)
                .build()
                .getBody();
        /*Subscriber to the Contact Listener so the Entity gets informed when ever a collison happens*/
        PhysicsController.worldContactListener.subscribe(this);


    }


}
