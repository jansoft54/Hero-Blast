package com.shooterman.game.Things;


import com.annimon.stream.Stream;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.Entity.EntityManager;
import com.shooterman.game.Component.PhysicComponent;
import com.shooterman.game.Observer.Observable;


public class WorldContact implements ContactListener {


    private EntityManager em;

    public WorldContact(EntityManager em) {
        this.em = em;
    }

    @Override
    public void beginContact(Contact contact) {
        System.out.println("Begin Contact");
        Fixture fixa = contact.getFixtureA();
        Fixture fixb = contact.getFixtureB();
        /*Check whether this is a valid collision*/
        if (fixa != null && fixb != null)
            if (fixa.getBody().getUserData() instanceof Entity && fixb.getBody().getUserData() instanceof Entity) {

                final Entity focusEntity = ((Entity) fixa.getBody().getUserData());
                final Entity otherEntity = ((Entity) fixb.getBody().getUserData());
                /*EnityManager's Keys are filtered for the collided Object Id's and then call tellEvent()*/
                Stream.of(em.getEntitys())
                        .filter(entry -> entry.getKey().equals(focusEntity.getId()) || entry.getKey().equals(otherEntity.getId()))
                        .forEach(entry -> ((PhysicComponent)entry.getValue().getComponent(PhysicComponent.class,true)).sendMessage(focusEntity,otherEntity));
            }
    }

    @Override
    public void endContact(Contact contact) {
        System.out.println("End Contact");

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {


    }
}
