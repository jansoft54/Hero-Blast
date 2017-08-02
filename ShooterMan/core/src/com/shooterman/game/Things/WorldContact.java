package com.shooterman.game.Things;


import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.shooterman.game.Observer.Observable;


public class WorldContact extends Observable implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        System.out.println("Begin Contact");
        Fixture fixa = contact.getFixtureA();
        Fixture fixb = contact.getFixtureB();
        /*Check whether this is a valid collision*/
        if (fixa != null && fixb != null) {
            setChanged();
            notifySuscribers(fixa, fixb);
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
