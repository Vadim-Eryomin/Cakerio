package ru.timetech.cakegame.defaults

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import ru.timetech.cakegame.enums.CoffeeType

open class Coffee: Object() {
    override var shape: Shape? = null
    lateinit var type: CoffeeType

    open fun create(sprite: Sprite, type: CoffeeType, world: World){
        super.create(shape as PolygonShape?, sprite, world)
        this.type = type
        shape = CircleShape()
        (shape as CircleShape).radius = 1f
    }
}