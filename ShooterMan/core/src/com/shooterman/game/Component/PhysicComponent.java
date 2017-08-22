package com.shooterman.game.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;
import com.shooterman.game.Component.IComponent.IMessage;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.BodyFactory;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController;
import com.shooterman.game.Observer.Observable;
import com.shooterman.game.Observer.Observer;

import org.jetbrains.annotations.NotNull;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;


public class PhysicComponent implements IComponent, IMessage {


    private Body body;
    private BodyFactory.Builder bodyBuilder;
    private Entity myEntity;

    public PhysicComponent(@NotNull Vector2 position, Entity myEntity, BodyDef.BodyType bodyType, World world) {
        this.myEntity = myEntity;

        this.bodyBuilder = new BodyFactory.Builder(position, bodyType, world, myEntity);
        this.body = bodyBuilder.build().getBody();


    }

    public PhysicComponent(Entity myEntity, BodyDef.BodyType bodyType, World world) {
        this.myEntity = myEntity;
        this.bodyBuilder = new BodyFactory.Builder(bodyType, world, myEntity);
        this.body = bodyBuilder.build().getBody();
    }

    public PhysicComponent addFixture(Shape shape, float friction, float restitution, float density, boolean isSensor) {
        bodyBuilder.addFixture(shape, friction, restitution, isSensor);
        return this;
    }

    void setLinearVelocity(float Vx, float Vy) {
        body.setLinearVelocity(Vx, Vy);
    }

    void applyImpulse() {

    }

    void addForce() {

    }

    @SafeVarargs
    @Override
    public final <T> void sendMessage(T... data) {
        if (myEntity.hasComponent(HealthComponent.class)) {
        //    System.out.println("Tell Event in PhysicComponent my id is " + myEntity.getId());
            ((HealthComponent) myEntity.getComponent(HealthComponent.class)).takeDamage(10);
        }

    }

    float getPositionX() {
        return body.getPosition().x * PPM;
    }

    float getPositionY() {
        return body.getPosition().y * PPM;
    }

    public Body getBody() {
        return body;
    }

    @Override
    public void update(float delta) {

    }


    @Override
    public void clearData() {

    }


}
