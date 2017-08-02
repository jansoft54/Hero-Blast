package com.shooterman.game.Refactor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.shooterman.game.Observer.Observable;
import com.shooterman.game.Observer.Observer;
import com.shooterman.game.Screens.PlayScreen;
import com.shooterman.game.TextureHolder;
import com.shooterman.game.Things.SingleAnimation;


import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;

public class Dragon implements GameObj, Observer {
    private Body body;
    private World world;
    private SingleAnimation animation;

    private float x;
    public float y;
    private float sinus;
    private float time;
    private float w;
    private float h;
    private float fixtureradX;
    private float fixtureradY;
    private ShapeRenderer shape;
    public int health = 100;
    public boolean isdead;
    private Fixture fixture;
    public int red;
    public int green;
    public ID id;
    private float shootintervall;
    private PlayScreen ps;
    private int shootlenght = 3;
    private static final int SPACING = 100;
    public boolean isHit;

    public enum ID {
        Boss, tinymonster

    }

    public Dragon(World world, float breite, float länge, PlayScreen playScreen) {
        super();
        this.world = world;
        this.shape = new ShapeRenderer();
        this.fixtureradX = breite;
        this.fixtureradY = länge;
        id = ID.tinymonster;
        ps = playScreen;
        shootlenght = MathUtils.random(2, 5);


        animation = new SingleAnimation(TextureHolder.dragon1anim, 1 / 10f);
        x = (float) MathUtils.random(1030, 1200);
        y = (float) MathUtils.random(50, 520);
        sinus = (float) (Math.random()) + 9;
        w = animation.getFrame(time).getRegionWidth() / 2;
        h = animation.getFrame(time).getRegionHeight() / 2;
        green = 255;
        setupBox2d();

    }


    @Override
    public Body getBody() {
        return body;
    }

    @Override

    public void update(float dt) {
        y = (float) (Math.sin(sinus * time)) * 2;


        if (id == ID.tinymonster) {
            body.setLinearVelocity(MathUtils.random(-0.2f, -2f), y);
        } else body.setLinearVelocity(0, y);

        if (time - shootintervall >= shootlenght) {
            shootlenght = 3;
            if (ps != null) {
                ps.activeMonstershots.add(new MonsterShot(ps, world, this, TextureHolder.monstershotFile));
            }
            shootintervall = time;
        }

        if (body.getPosition().x * PPM + 30 < 0) {


            isdead = true;
        }


    }

    @Override
    public void render(float dt, SpriteBatch sb) {
        update(dt);
        time += dt;
        sb.draw(animation.getFrame(time), body.getPosition().x * PPM - w, body.getPosition().y * PPM - h);
    }

    public void dispose() {
        ps.removeBodys.add(body);
    }

    public void setupBox2d() {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = id == ID.tinymonster ? BodyDef.BodyType.DynamicBody : BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x / PPM, y / PPM);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(fixtureradX / PPM, fixtureradY / PPM);
        fixtureDef.shape = polygonShape;
        fixtureDef.isSensor = id == ID.Boss;

        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);


        fixture.setUserData(this);
        body.setGravityScale(0);


    }

    public void setHealthbar(SpriteBatch sb) {
        if (animation != null) {
            if (health < 100 && health >= 5) {
                shape.setProjectionMatrix(sb.getProjectionMatrix());
                shape.begin(ShapeRenderer.ShapeType.Filled);
                shape.setColor(Color.WHITE);
                shape.rect(body.getPosition().x * PPM - w - 1, body.getPosition().y * PPM + h - 1, 52, 8);
                shape.setColor(Color.BLACK);
                shape.rect(body.getPosition().x * PPM - w, body.getPosition().y * PPM + h, 50, 6);
                shape.setColor(red / 255f, green / 255f, 0, 0);
                shape.rect(body.getPosition().x * PPM - w, body.getPosition().y * PPM + h, health / 2, 6);
                shape.end();
            } else if (health <= 5) {
                ps.removeBodys.add(body);
                isdead = true;

            }
        } else {
            if (health < 1000 && health >= 5) {
                shape.setProjectionMatrix(sb.getProjectionMatrix());
                shape.begin(ShapeRenderer.ShapeType.Filled);
                shape.setColor(Color.BLACK);
                shape.rect(body.getPosition().x * PPM, body.getPosition().y * PPM + animation.getFrame(Gdx.graphics.getDeltaTime()).getTexture().getHeight() / 2, 250, 12);
                shape.setColor(red / 255f, green / 255f, 0, 0);
                shape.rect(body.getPosition().x * PPM, body.getPosition().y * PPM + animation.getFrame(Gdx.graphics.getDeltaTime()).getTexture().getHeight() / 2, health / 4, 12);
                shape.end();
            } else if (health <= 5) {
                ps.removeBodys.add(body);
                isdead = true;
            }

        }

    }

    @Override
    public <T> void tellEvent(Observable ob, T[] data) {

        if (((Fixture) data[0]).getUserData() instanceof Dragon || ((Fixture) data[1]).getUserData() instanceof Dragon)
            if (data[0] instanceof Fixture && data[1] instanceof Fixture) {
            /*Now we can safely conclude that the dragon variable holds the reference to the Fixture of the dragon body
            * and the collidedObject holds the reference to the Fixture of the collided body object*/
                Fixture dragon = (Fixture) (((Fixture) data[0]).getUserData() instanceof Dragon ? data[0] : data[1]);
                Fixture collidedObject = (Fixture) (dragon.equals(data[0]) ? data[1] : data[0]);

                Object identity = collidedObject.getUserData();
                if (identity.equals("Upperspikes")) {
                    health -= 80;
                    red += 200;
                    green -= 200;

                } else if (identity instanceof Shot) {
                    health -= 80;
                    red += 200;
                    green -= 200;
                }

            }


    }


}
