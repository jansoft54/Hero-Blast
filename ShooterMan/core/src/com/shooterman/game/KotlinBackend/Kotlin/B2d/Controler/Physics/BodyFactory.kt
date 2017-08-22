package com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.shooterman.game.Component.Entity.Entity
import java.util.*


class BodyFactory private constructor(builder: Builder) {
    val body = builder.body
    val PPM = 100f

    class Builder(position: Vector2, type: BodyDef.BodyType, world: World, gravityScale: Float, identity: Entity) {
        var body: Body

        init {

            body = world.createBody(BodyDef().apply {

                this.position.set(position)
                this.type = type
                this.gravityScale = gravityScale
            })
            body.userData = identity
        }

        constructor(type: BodyDef.BodyType, world: World, identity: Entity) : this(Vector2((Random().nextFloat()*200+200)/100f,Random().nextFloat()*3), type, world, 0f, identity)
        constructor(position: Vector2,type: BodyDef.BodyType, world: World, identity: Entity) : this(position, type, world, 1f, identity)

        fun addFixture(shape: Shape?, friction: Float = 0.2f, restitution: Float = 0f, isSensor: Boolean = false): Builder {
            body.createFixture(FixtureDef().apply {
                this.shape = shape
                this.friction = friction;
                this.restitution = restitution
                this.isSensor = isSensor

            }).userData = body.userData

            return this
        }

        fun build(): BodyFactory = BodyFactory(this)

    }
}