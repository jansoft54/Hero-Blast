package com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.shooterman.game.Component.Entity.Entity


class BodyFactory private constructor(builder: Builder) {
    val body = builder.body

    class Builder(position: Vector2, type: BodyDef.BodyType, world: World, identity: Entity) {
        var body: Body

        init {

            body = world.createBody(BodyDef().apply {
                this.position.set(position)
                this.type = type
            })
            body.userData = identity
        }

        /*   fun setPosition(position: Vector2):Builder{
               body.position.set(position)
               return this
           }
       */
        /*  fun setGravityScale(scale: Float): Builder {
              body.gravityScale = scale
              return this
          }
  */
        fun addFixture(shape: Shape?, friction: Float = 0.2f, restitution: Float = 0f, isSensor: Boolean = false): Builder {
            body.createFixture(FixtureDef().apply {
                this.shape = shape
                this.friction = friction;
                this.restitution = restitution
                this.isSensor = isSensor
                this.density = 2.5f
            }).userData = body.userData

            return this
        }

        fun build(): BodyFactory = BodyFactory(this)

    }
}