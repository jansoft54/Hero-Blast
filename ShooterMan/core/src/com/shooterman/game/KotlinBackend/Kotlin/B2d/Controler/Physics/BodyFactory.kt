package com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*


class BodyFactory private constructor(builder: Builder) {
    val body = builder.body

    class Builder(position: Vector2, type: BodyDef.BodyType, world: World, identity: Any) {
        var body: Body

        init {
            val bodyDef: BodyDef = BodyDef()
            bodyDef.position.set(position)
            bodyDef.type = type
            body = world.createBody(bodyDef)
            body.userData = identity
        }
        fun setPosition(position: Vector2):Builder{
            body.position.set(position)
            return this
        }

        fun setGravityScale(scale: Float): Builder {
            body.gravityScale = scale
            return this
        }

        fun addFixture(shape: Shape?, friction: Float = 0.2f, restitution: Float = 0f, density: Float = 0f, isSensor: Boolean = false): Builder {
            val fixtureDef = FixtureDef()
            fixtureDef.shape = shape
            fixtureDef.friction = friction
            fixtureDef.restitution = restitution
            fixtureDef.density = density
            fixtureDef.isSensor = isSensor
            body.createFixture(fixtureDef).userData = body.userData

            return this
        }

        fun build(): BodyFactory = BodyFactory(this)

    }
}