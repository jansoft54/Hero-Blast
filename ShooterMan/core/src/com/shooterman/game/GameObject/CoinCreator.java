package com.shooterman.game.GameObject;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;



import com.badlogic.gdx.utils.Array;
import com.shooterman.game.GameObject.GameObj;
import com.shooterman.game.Screens.PlayScreen;
import com.shooterman.game.Things.SingleAnimation;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;


public class CoinCreator implements GameObj {
    public Texture cointexture = new Texture("coins/coin.png");
    private CoinFormats formation;
    private Body body;
    private World world;
    private Array<Body> bodies;
    public PlayScreen playScreen;
    public static Sound coinSound;
    private SingleAnimation coinAnim;
    private float time;

    public CoinCreator(World world, PlayScreen ps) {

        this.world = world;
        this.bodies = new Array<>();
        this.coinAnim = new SingleAnimation("Atlases/obstacleAnim/coinAnim.atlas", 1 / 6f);
        playScreen = ps;


    }

    public void setupFormation() {
        int rand = MathUtils.random(0, 10);
        if (rand <= 3 && rand <= 4) {
            CoinFormats.Line.setLine(1200, MathUtils.random(30, 550));
            formation = CoinFormats.Line;
        } else if (rand >= 4 && rand <= 7) {
            CoinFormats.Diagonal.setDiagonal(1200, MathUtils.random(30, 550));
            formation = CoinFormats.Diagonal;
        } else {
            CoinFormats.Bold.setBold(1200, MathUtils.random(30, 550));
            formation = CoinFormats.Bold;
        }
    }


    private enum CoinFormats {
        Line(), Diagonal(), Bold();


        int[] x_l;
        int[] y_l;

        void setLine(int x, int y) {

            x_l = new int[]{x += 0, x += 40, x += 40, x += 40, x += 40};
            y_l = new int[]{y, y, y, y, y};
        }

        void setDiagonal(int x, int y) {
            x_l = new int[]{x, x += 40, x += 40, x += 40, x += 40, x += 40, x += 40, x += 40, x += 40};
            y_l = new int[]{y, y += 20, y -= 20, y += 20, y -= 20, y += 20, y -= 20, y += 20, y -= 20};
        }

        void setBold(int x, int y) {
            x_l = new int[]{x, x += 0, x += 40, x += 40, x += 40, x -= 160, x += 40, x += 40, x += 40, x += 40, x += 40, x += 40, x -= 200, x += 40, x += 40, x += 40, x += 40};
            y_l = new int[]{y, y, y, y, y, y -= 40, y, y, y, y, y, y, y -= 40, y, y, y, y};
        }


    }

    @Override
    public Body getBody() {
        return null;
    }

    @Override
    public void update(float dt) {
        time += dt;

        for (Body body : bodies) {
            if (body.getPosition().x * PPM < 0) {
                bodies.removeValue(body, true);
                playScreen.removeBodys.add(body);
            }else
            body.setLinearVelocity(-4, 0);
        }

    }

    @Override
    public void render(float dt, SpriteBatch sb) {
        update(dt);
        for (Body body : bodies) {

            sb.draw(coinAnim.getFrame(time), body.getPosition().x * PPM - cointexture.getWidth() / 2, body.getPosition().y * PPM - cointexture.getHeight() / 2);
        }

    }

    @Override
    public void setupBox2d() {
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circle = new CircleShape();
        circle.setRadius(10 / PPM);


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        for (int i = 0; i < formation.x_l.length; i++) {
            fixtureDef.shape = circle;
            fixtureDef.isSensor = true;
            bodyDef.position.set(formation.x_l[i] / PPM, formation.y_l[i] / PPM);
            body = world.createBody(bodyDef);
            body.setGravityScale(0);
            body.createFixture(fixtureDef).setUserData(this);
            bodies.add(body);

        }

    }

    public void dispose() {
        for (int i = 0; i < bodies.size; i++) {

            playScreen.removeBodys.add(bodies.get(i));
            bodies.removeIndex(i);

        }
    }

    public void dispose(Body coin) {
        for (int i = 0; i < bodies.size; i++) {
            if (coin == bodies.get(i)) {
                playScreen.removeBodys.add(coin);
                bodies.removeIndex(i);
            }
        }


    }
}
