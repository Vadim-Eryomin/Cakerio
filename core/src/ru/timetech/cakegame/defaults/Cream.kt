package ru.timetech.cakegame.defaults

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import ru.timetech.cakegame.enums.CreamType

open class Cream: Object() {
    lateinit var defaultCreamShape: PolygonShape
    lateinit var type: CreamType
    private val poly = arrayOf(Vector2(0f, 0f), Vector2(5f, 0f), Vector2(5f, 1f),
            Vector2(4f, 1f), Vector2(4f, 0f), Vector2(1f, 0f),
            Vector2(1f, 1f), Vector2(0f, 1f), Vector2(0f,0f))

    fun create(shape: PolygonShape?, sprite: Sprite, type: CreamType, world: World) {
        super.create(shape, sprite, world)

        defaultCreamShape = PolygonShape()
        defaultCreamShape.set(poly)

        this.shape = shape ?: defaultCreamShape
        this.type = type
    }

}