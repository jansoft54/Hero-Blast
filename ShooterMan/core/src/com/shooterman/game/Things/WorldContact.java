package com.shooterman.game.Things;


import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.shooterman.game.GameObject.CoinCreator;
import com.shooterman.game.GameObject.Monsters.Dragon;
import com.shooterman.game.GameObject.Monsters.MonsterShot;
import com.shooterman.game.GameObject.Player;
import com.shooterman.game.GameObject.Shot;
import com.shooterman.game.Observer.Observable;


public class WorldContact extends Observable implements ContactListener {


    @Override
    public void beginContact(Contact contact) {
        Fixture fixa = contact.getFixtureA();
        Fixture fixb = contact.getFixtureB();
        Fixture object1;
        Fixture object2;
        if (fixa != null && fixb != null) {

            if (fixa.getUserData() instanceof Player || fixb.getUserData() instanceof Player) {
                //object1 ist der Player
                object1 = fixa.getUserData() instanceof Player ? fixa : fixb;
                //object2 ist nun das andere Objekt
                object2 = object1 == fixa ? fixb : fixa;

                if (object2.getUserData().equals("Upperspikes")) {
                    ((Player) object1.getUserData()).heart -= 1;
                    ((Player) object1.getUserData()).collided = true;
                    ((Player) object1.getUserData()).isHit = true;
                  //  if (((Player) object1.getUserData()).heart > 0)
                    //    hitSound();


                } else if (object2.getUserData() instanceof Dragon) {
                    ((Dragon) object2.getUserData()).isdead = true;


                 //   if (((Player) object1.getUserData()).heart > 0)
                    //    hitSound();

                    if (((Dragon) object2.getUserData()).id == Dragon.ID.Boss) {
                        ((Player) object1.getUserData()).heart -= 1;

                    } else {
                        ((Player) object1.getUserData()).heart -= 1;

                    }

                } else if (object2.getUserData() instanceof MonsterShot) {
                    ((Player) object1.getUserData()).heart--;
                    ((Player) object1.getUserData()).isHit = true;
                    ((MonsterShot) object2.getUserData()).collided = true;
                  //  if (((Player) object1.getUserData()).heart > 0)
                   //     hitSound();

                } else if (object2.getUserData() instanceof CoinCreator) {
                    ((CoinCreator) object2.getUserData()).dispose(object2.getBody());
                    //long id = CoinCreator.coinSound.play();
                    //CoinCreator.coinSound.setVolume(id,0.2f);

                    playScreen.player.coins++;

                }
            } else if (fixa.getUserData() instanceof Shot || fixb.getUserData() instanceof Shot) {
                //object1 ist der Schuss
                object1 = fixa.getUserData() instanceof Shot ? fixa : fixb;
                //object2 ist nun das andere Objekt
                object2 = object1 == fixa ? fixb : fixa;

                if (object2.getUserData() instanceof Dragon) {
                    if ((((Dragon) object2.getUserData()).id == Dragon.ID.tinymonster)) {

                        /*
                        Alles ersetzbar durch das Observerpattern
                         */
                        ((Dragon) object2.getUserData()).health -= 80;
                        ((Shot) object1.getUserData()).collided = true;
                        ((Dragon) object2.getUserData()).red += 200;
                        ((Dragon) object2.getUserData()).green -= 200;

                        if (((Dragon) object2.getUserData()).health <= 5) {
                            for (MonsterShot ms : playScreen.activeMonstershots) {
                                if (ms.dragon == ((Dragon) object2.getUserData())) {
                                    ms.collided = true;
                                    playScreen.removeBodys.add(ms.getBody());
                                }
                            }
                        }
                    } else {
                        ((Dragon) object2.getUserData()).health -= 5;
                        ((Shot) object1.getUserData()).collided = true;
                        ((Dragon) object2.getUserData()).red += 2;
                        ((Dragon) object2.getUserData()).green -= 2;
                    }

                }
            }


        }


    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixa = contact.getFixtureA();
        Fixture fixb = contact.getFixtureB();
        if (fixa.getUserData() instanceof Player || fixb.getUserData() instanceof Player) {
            //fix a ist der Player
            fixa = fixb.getUserData() instanceof Player ? fixb : fixa;
            ((Player) fixa.getUserData()).collided = false;
            // fixb ist der andere
        }


    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {


    }
}
