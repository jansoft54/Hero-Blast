package com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.shooterman.game.Things.WorldContact

class PhysicsController{
    init {
        PhysicsController.initWorld()
    }
    companion object {
        @JvmField
        val world:World = World(Vector2(0f,4.81f),true)
        @JvmField
        val worldContactListener:WorldContact = WorldContact()
        fun initWorld() = world.setContactListener(worldContactListener)

    }

}