package ru.timetech.cakegame.defaults

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import ru.timetech.cakegame.enums.CreamType

open class Object{
    open var shape: Shape? = null
    open lateinit var sprite: Sprite
    open lateinit var body: Body
    open lateinit var world: World
    open lateinit var def: BodyDef
    open lateinit var defaultShape: PolygonShape
    open lateinit var fixture: Fixture
    open fun create(shape: PolygonShape?, sprite: Sprite, world: World){
        this.shape = shape
        this.sprite = sprite
        this.world = world

        defaultShape = PolygonShape()
        defaultShape.setAsBox(sprite.width / 2f, sprite.height / 2f)

        def = BodyDef()
        def.type = BodyDef.BodyType.StaticBody
        body = world.createBody(def)
        fixture = body.createFixture(shape ?: defaultShape, 1f)
    }
    open fun update(){

    }

    open fun setPosition(position: Vector2){
        body.setTransform(Vector2(position.x + sprite.width / 2f, position.y + sprite.height / 2f), 0f)
        sprite.setPosition(position.x, position.y)
        sprite.rotation = body.angle
    }

    open fun setPosition(x: Float, y: Float){
        setPosition(Vector2(x, y))
    }

    open fun render(batch: SpriteBatch){
        sprite.setPosition(body.position.x - sprite.width / 2f, body.position.y - sprite.height / 2f)
        sprite.rotation = Math.toDegrees(body.angle.toDouble()).toFloat()
        sprite.draw(batch)
    }

    open fun spawn(position: Vector2){
        val body = world.createBody(def)
        body.setTransform(position, 0f)
        body.createFixture(shape, 1f)
    }

    open fun onClick(){

    }
}