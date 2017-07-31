package com.shooterman.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.shooterman.game.GameObject.CoinCreator;
import com.shooterman.game.GameObject.Monsters.Bat;
import com.shooterman.game.GameObject.Monsters.Dragon;
import com.shooterman.game.GameObject.Monsters.MonsterShot;
import com.shooterman.game.GameObject.Spikes;
import com.shooterman.game.GameObject.Player;
import com.shooterman.game.GameObject.Shot;
import com.shooterman.game.MainClass.ShooterMain;
import com.shooterman.game.TextureHolder;
import com.shooterman.game.Things.Parallaxutil;
import com.shooterman.game.Things.SingleAnimation;
import com.shooterman.game.Things.TouchpadInput;
import com.shooterman.game.Ui.UI;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController.world;
import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.*;

public class PlayScreen implements Screen {


    private OrthographicCamera bx2dcamera;//
    private OrthographicCamera gamecam;
    public Player player;
    private SpriteBatch sb;
    private Stage stage;//
    private TouchpadInput touchpad;
    private StretchViewport stViewport;
    private float time;

    private Array<Shot> activeShots;//
    private Array<Dragon> activeMonsters;//
    public Array<MonsterShot> activeMonstershots;//
    public Array<Body> removeBodys;//
    private Array<Spikes> obstacles;//
    private SingleAnimation animation;//


    private Box2DDebugRenderer box2DDebugRenderer;
    private Parallaxutil parallaxutil;
    public boolean isactive = true;//
    private boolean tutorialPlayed;//
    private boolean knoptouched;//
    private Vector2 direction;//
    private Texture heart;//
    private CoinCreator coincreator;
    private float handposition = 200;//
    private Bat bat;
    public boolean gameover;//


    public PlayScreen(ShooterMain sm, OrthographicCamera camera) {

        this.gamecam = camera;
        this.bx2dcamera = new OrthographicCamera();
        this.sb = new SpriteBatch();
        this.stage = new Stage(new StretchViewport(ShooterMain.WIDTH, ShooterMain.HEIGHT));
        this.touchpad = new TouchpadInput(stage, this);
        this.activeShots = new Array<>();
        this.activeMonsters = new Array<>();
        this.obstacles = new Array<>();
        this.activeMonstershots = new Array<>();
        this.coincreator = new CoinCreator(world, this);
        this.removeBodys = new Array<>();

        this.animation = new SingleAnimation("Atlases/buttonatlas/hand.atlas", 1 / 3f);
        bat = new Bat(this, world, "Atlases/enemyAnimation/fleder.atlas");


        // music.play();
        direction = new Vector2();
        heart = new Texture("enviroment/heart.png");

        stViewport = new StretchViewport(ShooterMain.WIDTH, ShooterMain.HEIGHT, gamecam);
        gamecam.setToOrtho(false, ShooterMain.WIDTH, ShooterMain.HEIGHT);
        stViewport.apply();

        player = new Player(this);

        bx2dcamera.setToOrtho(false, ShooterMain.WIDTH / PPM, ShooterMain.HEIGHT / PPM);
        parallaxutil = new Parallaxutil();

        UI ui = new UI(this);
        ui.addActor(stage);

        obstacles.add(new Spikes(TextureHolder.upperspikes, world, "Upperspikes", TextureHolder.upperspikes.getWidth() / 2, 600));


        box2DDebugRenderer = new Box2DDebugRenderer();

        activeMonsters.add(new Dragon(world, 32, 20, this));
        activeMonsters.add(new Dragon(world, 32, 20, this));
        activeMonsters.add(new Dragon(world, 32, 20, this));
    }

    @Override
    public void render(float delta) {
        update(delta);
        gamecam.update();
        sb.setProjectionMatrix(gamecam.combined);
        sb.begin();

        parallaxutil.render(sb);
        player.render(sb);
        coincreator.render(delta, sb);

        drawAnimations(sb);


        if (!tutorialPlayed)
            drawTutorial();

        if (!isactive || !tutorialPlayed) {

            if (!isactive)
                sb.draw(TextureHolder.blackPause, 0, 0);
            world.step(0, 0, 0);
        } else {
            world.step(delta, 6, 2);
        }

        bat.render(delta, sb);
        sb.end();

        renderHealthbar();
        touchpad.draw();
        box2DDebugRenderer.render(world, bx2dcamera.combined);

        for (Body body : removeBodys) {
            if (body.getFixtureList().size > 0)

                body.destroyFixture(body.getFixtureList().first());

            removeBodys.removeValue(body, true);

        }

    }

    @Override
    public void resize(int width, int height) {
        stViewport.update(width, height);
    }

    private void drawTutorial() {

        sb.draw(animation.getFrame(time), !tutorialPlayed && knoptouched && handposition <= 700 ? handposition += 1200 * Gdx.graphics.getDeltaTime() : handposition, activeMonsters.get(0).y * 4 + 150);

    }

    private void update(float dt) {
        player.update(dt);
        handleInput();
        time += dt;


        //Hier werden die Coins gespawnt
        if (time >= 10f && tutorialPlayed) {
            time = 0;
            coincreator.dispose();
            coincreator.setupFormation();
            coincreator.setupBox2d();
        }

        //Der Hintergrund bewegt sich nachhinten
        parallaxutil.update();

        direction.set(0.2f * touchpad.getTouchpad().getKnobPercentX(), 0.16f * touchpad.getTouchpad().getKnobPercentY());

        // das Touchpad wurde berührt, jetzt  muss der Screen berührt werden
        if (direction.x != 0 && direction.y != 0) {
            knoptouched = true;
        }

        //Hier ist das Movement es wird die lineare Geschwindigkeit gesetzt
        if (touchpad.getTouchpad().getKnobPercentX() != 0f && touchpad.getTouchpad().getKnobPercentY() != 0f && !gameover)
            player.getBody().setLinearVelocity(direction.x * 20, direction.y * 20);


    }

    private void handleInput() {

        if (Gdx.input.justTouched() && isactive && !gameover) {
            if (knoptouched)
                tutorialPlayed = true;
                /* if (player.isflipped)
                activeShots.add(new Shot(player, world, -1));
                */
            activeShots.add(new Shot(player, world, 1));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            Shot shot;
            /*if (player.isflipped)
                shot = new Shot(player, world, -1);
                */
            activeShots.add(new Shot(player, world, 1));

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
    }


    private void drawAnimations(SpriteBatch sb) {
        int diff = 5 - player.heart;
        int padding = 0;

        if (player.heart > 0) {

            for (int i = 0; i < player.heart; i++) {
                sb.setColor(1, 1, 1, 1);
                sb.draw(heart, padding += 30, 480);
            }
            for (int i = 0; i < diff; i++) {
                sb.setColor(0.5f, 0.5f, 0.5f, 0.5f);
                sb.draw(heart, padding += 30, 480);
            }
            sb.setColor(1, 1, 1, 1);

        }
        for (int i = 0; i < activeMonsters.size; i++) {
            if (!activeMonsters.get(i).isdead)
                activeMonsters.get(i).render(Gdx.graphics.getDeltaTime(), sb);
            else {
                removeBodys.add(activeMonsters.get(i).getBody());
                activeMonsters.removeIndex(i);
                activeMonsters.add(new Dragon(world, 32, 27, this));

            }
        }
        for (int i = 0; i < activeMonstershots.size; i++) {
            if (!activeMonstershots.get(i).collided)
                activeMonstershots.get(i).render(Gdx.graphics.getDeltaTime(), sb);
            else
                activeMonstershots.removeIndex(i);
        }

        for (int i = 0; i < activeShots.size; i++) {

            if (!activeShots.get(i).collided && (!(activeShots.get(i).getBody().getPosition().x * PPM < 0) && !(activeShots.get(i).getBody().getPosition().x * PPM > gamecam.viewportWidth + 40))) {
                activeShots.get(i).render(Gdx.graphics.getDeltaTime(), sb);
            } else {

                activeShots.get(i).dispose();
                removeBodys.add(activeShots.get(i).getBody());
                activeShots.removeIndex(i);
            }
        }


    }


    private void renderHealthbar() {
        for (int i = 0; i < activeMonsters.size; i++) {
            activeMonsters.get(i).setHealthbar(sb);
        }
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


        player.dispose();


        TextureHolder.upperspikes.dispose();
        TextureHolder.blackPause.dispose();
        CoinCreator.coinSound.dispose();


        for (Shot shot : activeShots) {
            shot.dispose();
        }
        for (Spikes spikes : obstacles)
            spikes.dispose();
        for (MonsterShot mshot : activeMonstershots)


            player.superAtlas.dispose();


    }

}
