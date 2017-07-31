package com.shooterman.game.GameObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.BodyFactory;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsControler;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController;
import com.shooterman.game.Observer.Observable;
import com.shooterman.game.Observer.Observer;
import com.shooterman.game.Screens.PlayScreen;


import javax.xml.soap.Text;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;

public class Player implements Observer {

    private Body body;
    public TextureAtlas superAtlas;
    private Animation<TextureRegion> superanimUp;
    private Animation<TextureRegion> superanimDown;
    private float time;

    public boolean collided;//
    public int heart = 5;

    private Array<TextureRegion> arrayframesUp;
    private Array<TextureRegion> arrayframesDown;
    private PlayScreen ps;


    public Player(PlayScreen ps) {

        this.ps = ps;
        superAtlas = AssetsManager.Manager.getManager().get("Atlases/Playeranim/player.atlas");
        arrayframesUp = new Array<>();
        arrayframesDown = new Array<>();
        setUpAnim();
        superanimUp = new Animation<>(1 / 5f, arrayframesUp);
        superanimDown = new Animation<>(1 / 5f, arrayframesDown);
        setupBox2d();

    }

    public Body getBody() {
        return body;
    }

    private void setUpAnim() {
        Texture textureUp = superAtlas.findRegion("S_oben").getTexture();
        Texture textureDown = superAtlas.findRegion("S_unten").getTexture();
        for (int j = 0; j <= 1; j++)
            arrayframesUp.add(new TextureRegion(textureUp, 95 * j, 0, 92, 64));
        for (int j = 2; j <= 3; j++)
            arrayframesDown.add(new TextureRegion(textureDown, 95 * j, 0, 92, 64));
    }

    public void update(float dt) {
        time += dt;
        if (collided)
            heart--;
        if (body.getPosition().x * PPM + 60 < 0)
            heart--;

    }
    /**Gets called when there is a new Event for which the "Player" instance has suscribed*/
    @Override
    public <T> void tellEvent(Observable ob, T[] d) {

    }

    public void render(SpriteBatch sb) {
        TextureRegion region = (body.getLinearVelocity().y <= -1) ? superanimUp.getKeyFrame(time, true) : superanimDown.getKeyFrame(time, true);

        if (!region.isFlipX())
            region.flip(true, false);
        else if (region.isFlipX())
            region.flip(true, false);

        if (heart > 0)
            sb.draw(region, body.getPosition().x * PPM - region.getRegionWidth() / 2, body.getPosition().y * PPM - region.getRegionHeight() / 2);
        else
            ps.gameover = true;

    }

    private void setupBox2d() {
        /*Initialize the Box2D relatet properties of the Player.
         BodyFactory uses the Builder-Pattern*/
        PolygonShape polyShape = new PolygonShape();
        polyShape.setAsBox(32 / PPM, 32 / PPM);
        body = new BodyFactory
                .Builder(new Vector2(300 / PPM, 200 / PPM), BodyDef.BodyType.DynamicBody, PhysicsController.world, this)
                .addFixture(polyShape, 0.2f, 0.01f, 0, false)
                .build()
                .getBody();
    }

    public void dispose() {
        for (TextureRegion region:arrayframesDown)
            region.getTexture().dispose();
        for (TextureRegion region: arrayframesUp)
            region.getTexture().dispose();
    }

}
