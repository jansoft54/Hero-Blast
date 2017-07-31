package com.shooterman.game.GameObject.Monsters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.shooterman.game.GameObject.GameObj;
import com.shooterman.game.Screens.PlayScreen;
import com.shooterman.game.Things.SingleAnimation;

/**
 * Created by master on 22.10.2016.
 */

public class MonsterShot implements GameObj {

    private SingleAnimation animation;
    private float time;
    private Body body;
    private World world;
    public  Dragon dragon;
    private PlayScreen ps;
    public boolean collided;
    private float lifetime = 2.5f;

    public MonsterShot(PlayScreen ps, World world, Dragon dragon, String File) {
        this.ps = ps;
        animation = new SingleAnimation(File,1/10f);
        this.world = world;
        this.dragon = dragon;
        setupBox2d();
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public void update(float dt) {
        body.setLinearVelocity(-3, 0);
        lifetime -= dt;
        float lifetime = this.lifetime;
        if (lifetime <= 0) {
            collided = true;
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
        BodyDef b = new BodyDef();
        b.type = BodyDef.BodyType.DynamicBody;

        b.position.set(dragon.getBody().getPosition().x, dragon.getBody().getPosition().y);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(20 /120f , 20 /150f);
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        body = world.createBody(b);
        body.setGravityScale(0);

        body.createFixture(fixtureDef).setUserData(this);


    }
}
