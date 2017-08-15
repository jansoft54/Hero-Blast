package com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.shooterman.game.Component.Entity.EntityManager
import com.shooterman.game.Observer.Observer
import com.shooterman.game.Things.WorldContact

class PhysicsController {


    companion object {

        @JvmField
        val world: World = World(Vector2(0f, -9.81f), true)

        lateinit var worldContactListener: WorldContact
        @JvmStatic
        fun initWorld(em: EntityManager) {
            worldContactListener = WorldContact(em)
            world.setContactListener(worldContactListener)
        }

        @JvmStatic
        fun update(delta: Float) = world.step(delta, 6, 2)

        //@JvmStatic
      //  fun subscribe(entity: Observer) = worldContactListener.subscribe(entity)

    }


}