package com.shooterman.game.Things;


import com.annimon.stream.Stream;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.Entity.EntityManager;
import com.shooterman.game.Component.PhysicComponent;
import com.shooterman.game.Observer.Observable;


public class WorldContact extends Observable implements ContactListener {


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
        if (fixa != null && fixb != null) {
            /*setChanged();
            notifySuscribers(fixa, fixb);*/


            final Entity entityOne = ((Entity) fixa.getBody().getUserData()).getId().equals("player")
                    ? (Entity) fixa.getBody().getUserData()
                    : (Entity) fixb.getBody().getUserData();
            final Entity entityTwo = ((Entity) fixb.getBody().getUserData()).getId().equals("player")
                    ? (Entity) fixb.getBody().getUserData()
                    : (Entity) fixa.getBody().getUserData();

            Stream.of(em.getEntitys())
                    .filter(entity -> entity.getValue().getId().equals(entityTwo.getId()))
                    .forEach(entity -> ((PhysicComponent) entity.getValue().getComponent(PhysicComponent.class, true)).tellEvent(this, entityOne, entityTwo));
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
