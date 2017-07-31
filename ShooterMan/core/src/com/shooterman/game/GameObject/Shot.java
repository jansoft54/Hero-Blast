package com.shooterman.game.GameObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.shooterman.game.TextureHolder;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.*;


public class Shot implements GameObj {
    private float playerX;
    private float playerY;
    public Sprite shot;
    private Body body;
    private World world;
    private Vector2 impulse;
    public boolean collided;
    private Fixture fixture;
    private Player player;
    public int scalefaktor;

    public Shot(Player player, World world, int scalefaktor) {
        this.world = world;
        this.player = player;
        this.playerX = player.getBody().getPosition().x * PPM;
        this.playerY = player.getBody().getPosition().y * PPM - 32;
        this.scalefaktor = scalefaktor;
        shot = new Sprite(new Texture(TextureHolder.shotRed));
        shot.setPosition(playerX, playerY);
        impulse = new Vector2(scalefaktor * 60f * Gdx.graphics.getDeltaTime(), 0f);
        setupBox2d();

    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public void update(float dt) {

        body.setLinearVelocity(scalefaktor < 0 ? -20 : 20, 0);
        shot.setPosition(body.getPosition().x*PPM+20        ,body.getPosition().y*PPM -shot.getHeight()/2);


    }

    @Override
    public void render(float dt, SpriteBatch sb) {
        update(dt);

        shot.draw(sb);

    }

    public void setupBox2d() {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(((playerX+10) / PPM), (playerY + 27) / PPM);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(30 / PPM, 7 / PPM);
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;
        body = world.createBody(bodyDef);
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
        body.setGravityScale(0);


    }

    public void dispose() {
        shot.getTexture().dispose();

    }
}
