package ru.timetech.cakegame.defaults

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import ru.timetech.cakegame.enums.PieType

open class Pie : Object() {
    open fun create(shape: PolygonShape?, sprite: Sprite, type: PieType, world: World) {
        super.create(shape, sprite, world)
        this.shape = PolygonShape()
        (this.shape as PolygonShape).setAsBox(sprite.width / 2.0f, sprite.height / 2.0f)
        body.type = BodyDef.BodyType.DynamicBody
        fixture.userData = "pie"
    }
}