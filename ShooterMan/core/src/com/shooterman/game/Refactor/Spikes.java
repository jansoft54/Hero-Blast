package com.shooterman.game.Refactor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.shooterman.game.Refactor.GameObj;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;

public class Spikes extends Sprite implements GameObj {
    private Body body;
    private Body body2;
    private World world;
    public String id;
    private int posX;
    private int posY;


    public Spikes(Texture texture, World world, String id, int posX, int posY) {
        this.world = world;
        this.id = id;
        this.posX = posX;
        this.posY = posY;
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        setTexture(texture);

        setupBox2d();
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public void update(float dt) {


    }


    @Override
    public void render(float dt, SpriteBatch sb) {
        update(dt);
        sb.draw(getTexture(), ((body.getPosition().x * PPM) - getTexture().getWidth() / 2), (body.getPosition().y * PPM) - getTexture().getHeight() / 2);
    }

    @Override
    public void setupBox2d() {
        BodyDef bodyDef = new BodyDef();
        BodyDef bodyDef2 = new BodyDef();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posX / PPM, posY / PPM);

        bodyDef2.type = BodyDef.BodyType.StaticBody;
        bodyDef2.position.set(0+getTexture().getWidth()/2/ PPM, 0 / PPM);

        FixtureDef fdef = new FixtureDef();
        PolygonShape polyshape1 = new PolygonShape();
        polyshape1.setAsBox(getTexture().getWidth() / 2 / PPM, 20 / PPM);

        fdef.shape = polyshape1;




        body = world.createBody(bodyDef);
        body2 = world.createBody(bodyDef2);



        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polyshape2 = new PolygonShape();

        polyshape2.setAsBox((getTexture().getWidth()/2)/PPM,20/PPM);
        fixtureDef.shape = polyshape2;

        body.createFixture(fdef).setUserData(id);
        body2.createFixture(fixtureDef).setUserData(id);


    }
    public void dispose(){
        getTexture().dispose();
    }

}
