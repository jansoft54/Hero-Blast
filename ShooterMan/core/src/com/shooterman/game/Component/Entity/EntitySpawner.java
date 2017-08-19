package com.shooterman.game.Component.Entity;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.shooterman.game.Component.AnimationComponent;
import com.shooterman.game.Component.HealthBarComponent;
import com.shooterman.game.Component.InputComponent;
import com.shooterman.game.Component.PhysicComponent;
import com.shooterman.game.Component.RenderComponent;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController;

import static com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager.Manager;
import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;
import static com.shooterman.game.MainClass.ShooterMain.HEIGHT;
import static com.shooterman.game.MainClass.ShooterMain.WIDTH;

public final class EntitySpawner {
   private EntityManager em;
   private SpriteBatch sb;

   public EntitySpawner(SpriteBatch sb,EntityManager em)
   {
       this.em = em;
       this.sb = sb;
   }


    public Entity makeDragon() {

        Entity entity = em.makeEntity("block");
        em.addEntityToWorld(entity);
        PolygonShape polygonShape1 = new PolygonShape();
        polygonShape1.setAsBox(50 / PPM, 5 / PPM);

        PhysicComponent physicComponent1 = new PhysicComponent(new Vector2(200, 160), entity, BodyDef.BodyType.StaticBody, PhysicsController.world);
        physicComponent1.addFixture(polygonShape1, 0.2f, 0f, 0f, false);

        em.addEntityComponent(entity.getId(), physicComponent1);
        return entity;
    }


    public Entity makePlayer() {
        Entity entity = em.makeEntity("player");
        em.addEntityToWorld(entity);
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(45 / PPM, 32 / PPM);

        PhysicComponent physicComponent = new PhysicComponent(new Vector2(((WIDTH +100/ 2) - 45), ((HEIGHT / 2) - 32)), entity, BodyDef.BodyType.DynamicBody, PhysicsController.world);
        physicComponent.addFixture(polygonShape, 0.2f, 0f, 0f, false);

        em.addEntityComponent(entity.getId(), physicComponent);
        em.addEntityComponent(entity.getId(), new RenderComponent(sb, entity));
        em.addEntityComponent(entity.getId(), new AnimationComponent(entity).addAnimation("playerFly", 1 / 5f, Animation.PlayMode.LOOP, ((TextureAtlas) Manager.getManager().get("Atlases/Playeranim/player.atlas")).getRegions()));
        em.addEntityComponent(entity.getId(), new InputComponent(entity));
        em.addEntityComponent(entity.getId(), new HealthBarComponent(entity, 200, 92, 10));
        return entity;
    }

    public Entity makeBigDragon() {
        return null;
    }

    public Entity makeBat() {
        return null;
    }


    private enum listedEntitys {
        DRAGON, PLAYER, BIGDRAGON, BAT

    }
}
