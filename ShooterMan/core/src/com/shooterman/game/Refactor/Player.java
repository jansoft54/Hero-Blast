package com.shooterman.game.Refactor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.BodyFactory;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController;
import com.shooterman.game.Observer.Observable;
import com.shooterman.game.Observer.Observer;
import com.shooterman.game.Screens.PlayScreen;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;

public class Player implements Observer {

    private Body body;
    public TextureAtlas superAtlas;
    private Animation<TextureRegion> superanimUp;
    private Animation<TextureRegion> superanimDown;
    private float time;
    public int hearts = 5;

    private Array<TextureRegion> arrayframesUp;
    private Array<TextureRegion> arrayframesDown;
    private PlayScreen ps;


    public Player(PlayScreen ps) {

        this.ps = ps;
        superAtlas = AssetsManager.Manager.getManager().get("Atlases/Playeranim/player.atlas");
        arrayframesUp = new Array<>();
        arrayframesDown = new Array<>();

        superanimUp = new Animation<>(1 / 5f, arrayframesUp);
        superanimDown = new Animation<>(1 / 5f, arrayframesDown);
        setUpAnim();
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
        if (body.getPosition().x * PPM + 60 < 0)
            hearts--;

    }

    /*
     * Gets called when there is a new Event for which the "Player" instance has subscribed
     */
    @Override
    public <T> void tellEvent(Observable ob, T[] data) {
        if (((Fixture) data[0]).getUserData() instanceof Player || ((Fixture) data[1]).getUserData() instanceof Player)
            if (data[0] instanceof Fixture && data[1] instanceof Fixture) {
            /*Now we can safely conclude that the player variable holds the reference to the Fixture of the Player body
            * and the collidedObject holds the reference to the Fixture of the collided body object*/
                Fixture player = (Fixture) (((Fixture) data[0]).getUserData() instanceof Player ? data[0] : data[1]);
                Fixture collidedObject = (Fixture) (player.equals(data[0]) ? data[1] : data[0]);

                Object identity = collidedObject.getUserData();
                if (identity.equals("Upperspikes"))
                    hearts--;
                else if (identity instanceof Dragon)
                    hearts--;
                else if (identity instanceof MonsterShot)
                    hearts--;
                else if (identity instanceof CoinCreator)
                    ((CoinCreator) identity).dispose(collidedObject.getBody());
            }

    }

    public void render(SpriteBatch sb) {
       /* boolean d = (body.getLinearVelocity().y <= -1);
        System.out.print(superAtlas.findRegion("S_oben").getTexture());
        TextureRegion region = d ? superanimUp.getKeyFrame(time, true) : superanimDown.getKeyFrame(time, true);

        if (!region.isFlipX())
            region.flip(true, false);
        else if (region.isFlipX())
            region.flip(true, false);

        if (hearts > 0)
            sb.draw(region, body.getPosition().x * PPM - region.getRegionWidth() / 2, body.getPosition().y * PPM - region.getRegionHeight() / 2);
        else
            ps.gameover = true;
            */

    }

    private void setupBox2d() {
        /*Initialize the Box2D relatet properties of the Player. BodyFactory uses the Builder-Pattern*/
        PolygonShape polyShape = new PolygonShape();
        polyShape.setAsBox(32 / PPM, 32 / PPM);
        body = new BodyFactory
                .Builder(new Vector2(300 / PPM, 200 / PPM), BodyDef.BodyType.DynamicBody, PhysicsController.world, this)
                .addFixture(polyShape, 0.2f, 0.01f, 0, false)
                .build()
                .getBody();
        /*Suscribes to the Contact Listener so the Entity gets informed when ever a collison happens*/
        PhysicsController.worldContactListener.subscribe(this);
    }

    public void dispose() {
        for (TextureRegion region : arrayframesDown)
            region.getTexture().dispose();
        for (TextureRegion region : arrayframesUp)
            region.getTexture().dispose();
    }
}
