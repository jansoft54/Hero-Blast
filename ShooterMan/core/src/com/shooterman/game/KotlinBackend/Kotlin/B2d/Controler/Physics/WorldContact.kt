package com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics


import com.annimon.stream.Stream
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.ContactListener
import com.shooterman.game.Component.Entity.Entity
import com.shooterman.game.Component.Entity.EntityManager
import com.shooterman.game.Component.PhysicComponent
import com.shooterman.game.Observer.Observable


class WorldContact(private val em: EntityManager) : ContactListener {

    override fun beginContact(contact: Contact) {
        println("Begin Contact")
        val fixa = contact.fixtureA
        val fixb = contact.fixtureB
        /*Check whether this is a valid collision*/
        if (fixa != null && fixb != null)
            if (fixa.body.userData is Entity && fixb.body.userData is Entity) {

                val focusEntity = fixa.body.userData as Entity
                val otherEntity = fixb.body.userData as Entity
                /*EnityManager's Keys are filtered for the collided Object Id's and then call tellEvent()*/
                Stream.of(em.entitys)
                        .filter { entry -> entry.key == focusEntity.id || entry.key == otherEntity.id }
                        .forEach { entry -> (entry.value.getComponent(PhysicComponent::class.java, true) as PhysicComponent).sendMessage(focusEntity, otherEntity) }
            }
    }

    override fun endContact(contact: Contact) {
        println("End Contact")

    }

    override fun preSolve(contact: Contact, oldManifold: Manifold) {

    }

    override fun postSolve(contact: Contact, impulse: ContactImpulse) {


    }
}
