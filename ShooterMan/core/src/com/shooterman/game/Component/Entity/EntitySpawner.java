package com.shooterman.game.Component.Entity;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.shooterman.game.Component.AnimationComponent;
import com.shooterman.game.Component.EnemyComponent;
import com.shooterman.game.Component.HealthBarComponent;
import com.shooterman.game.Component.InputComponent;
import com.shooterman.game.Component.PhysicComponent;
import com.shooterman.game.Component.RenderComponent;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController;
import com.shooterman.game.ShootComponent;

import static com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager.Manager;
import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;
import static com.shooterman.game.MainClass.ShooterMain.HEIGHT;
import static com.shooterman.game.MainClass.ShooterMain.WIDTH;

public final class EntitySpawner {
    private EntityManager em;
    private SpriteBatch sb;


    public EntitySpawner(SpriteBatch sb, EntityManager em) {
        this.em = em;
        this.sb = sb;

    }


    public Entity makeDragon() {

        Entity entity = em.makeEntity("dragon".concat(String.valueOf(Math.random())));
        em.addEntityToWorld(entity);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(20 / PPM, 10 / PPM);
        float randX = (float) Math.random() * 200 + 200;
        float randY = ((float) Math.random() * 200) + 200;


        PhysicComponent physicComponent = new PhysicComponent(new Vector2(randX / PPM, randY / PPM), entity, BodyDef.BodyType.KinematicBody, PhysicsController.world);
        physicComponent.addFixture(polygonShape, 0.2f, 0f, 0f, false);
        em.addEntityComponent(entity.getId(), physicComponent);
        em.addEntityComponent(entity.getId(), new RenderComponent(sb, entity));
        AnimationComponent animationComponent = new AnimationComponent(entity);
        TextureAtlas textureAtlas = Manager.getManager().get("Atlases/enemyAnimation/dragon.atlas");
        animationComponent.addAnimation("dragonFly", 1 / 10f, Animation.PlayMode.LOOP, textureAtlas.getRegions());



        em.addEntityComponent(entity.getId(), new EnemyComponent(entity));
        em.addEntityComponent(entity.getId(), new HealthBarComponent(entity, 200, 70, 5));
        em.addEntityComponent(entity.getId(), new ShootComponent(entity, this));
        em.addEntityComponent(entity.getId(), animationComponent);
      //  em.addEntityComponent(entity.getId(), new ShootComponent(entity, this));
        return entity;
    }

    public Entity makeBarriers(float x, float y) {
        Entity entity = em.makeEntity("barrier".concat(String.valueOf(System.currentTimeMillis() / 1000)));
        em.addEntityToWorld(entity);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(WIDTH / 2 / PPM, 10 / PPM);

        PhysicComponent physicComponent1 = new PhysicComponent(new Vector2(x / PPM, y / PPM), entity, BodyDef.BodyType.KinematicBody, PhysicsController.world);
        physicComponent1.addFixture(polygonShape, 0.2f, 0f, 0f, false);

        em.addEntityComponent(entity.getId(), physicComponent1);

        return entity;
    }

    public Entity makeShot(Body body) {

        Entity entity = em.makeEntity("Shot".concat(String.valueOf(Math.random())));
        em.addEntityToWorld(entity);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(20 / PPM, 10 / PPM);


        PhysicComponent physicComponent = new PhysicComponent(new Vector2(body.getPosition().x, body.getPosition().y), entity, BodyDef.BodyType.KinematicBody, PhysicsController.world);
        physicComponent.addFixture(polygonShape, 0.2f, 0f, 0f, false);

        physicComponent.getBody().setLinearVelocity(-4,0);
        em.addEntityComponent(entity.getId(), physicComponent);
        em.addEntityComponent(entity.getId(), new RenderComponent(sb, entity));
        AnimationComponent animationComponent = new AnimationComponent(entity);
        TextureAtlas textureAtlas = Manager.getManager().get("Atlases/enemyShots/fireballlow.atlas");
        animationComponent.addAnimation("shot", 1 / 10f, Animation.PlayMode.LOOP, textureAtlas.getRegions());


        em.addEntityComponent(entity.getId(), animationComponent);
        return entity;


    }

    public Entity makePlayer() {
        Entity entity = em.makeEntity("player");
        em.addEntityToWorld(entity);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(45 / PPM, 32 / PPM);

        PhysicComponent physicComponent = new PhysicComponent(new Vector2(WIDTH / 2 / PPM, HEIGHT / 2 / PPM), entity, BodyDef.BodyType.DynamicBody, PhysicsController.world);
        physicComponent.addFixture(polygonShape, 0.2f, 0f, 0f, false);
        em.addEntityComponent(entity.getId(), physicComponent);
        em.addEntityComponent(entity.getId(), new RenderComponent(sb, entity));
        AnimationComponent animationComponent = new AnimationComponent(entity);
        TextureAtlas textureAtlas = Manager.getManager().get("Atlases/Playeranim/player.atlas");
        animationComponent.addAnimation("playerFly", 1 / 5f, Animation.PlayMode.LOOP, textureAtlas.getRegions());



        em.addEntityComponent(entity.getId(), animationComponent);
        em.addEntityComponent(entity.getId(), new InputComponent(entity));
        em.addEntityComponent(entity.getId(), new HealthBarComponent(entity, 200, 92, 10));
     //   em.addEntityComponent(entity.getId(), new ShootComponent(entity, this));

        return entity;
    }


    public Entity makeBigDragon() {
        return null;
    }

    public Entity makeBat() {
        return null;
    }


}
