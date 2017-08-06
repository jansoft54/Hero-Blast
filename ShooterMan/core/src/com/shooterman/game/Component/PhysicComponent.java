package com.shooterman.game.Component;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.BodyFactory;
import com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.PhysicsController;

import org.jetbrains.annotations.Nullable;


public class PhysicComponent implements IComponent {


    private Vector2 position;
    private Body body;
    private BodyFactory.Builder bodyBuilder;
    private Entity myEntity;

    public PhysicComponent(@Nullable Vector2 position, Entity myEntity) {
        this.position = position;
        this.myEntity = myEntity;
    }

    void addBody(@Nullable Vector2 position, BodyDef.BodyType bodyType, Object identity) {
        bodyBuilder = new BodyFactory.Builder(position != null ? position : this.position, bodyType, PhysicsController.world, identity);
        body = bodyBuilder.build().getBody();
    }

    void addFixture(Shape shape, float friction, float restitution, float density, boolean isSensor) {
        bodyBuilder.addFixture(shape, friction, restitution, density, isSensor);
    }

    void setLinearVelocity(float Vx,float Vy) {
        body.setLinearVelocity(Vx,Vy);
    }

    void applyImpulse() {

    }

    void addForce() {

    }


    Vector2 getPosition() {
        return position;
    }

    void setPosition(float x, float y) {
        position.set(position.x+x, position.y+y);
    }

    @Override
    public void update(float delta) {

    }


    @Override
    public void clearData() {

    }
}
