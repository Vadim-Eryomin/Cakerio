package ru.timetech.cakegame.listeners

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import ru.timetech.cakegame.CakeGame

class InputListener(private var game: CakeGame): InputProcessor {
    private val def = BodyDef()
    private val shape = PolygonShape()

    init {
        def.type = BodyDef.BodyType.DynamicBody
        shape.setAsBox(10f, 10f)
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val body = game.world.createBody(def)
        body.setTransform(screenX.toFloat(), Gdx.graphics.height - screenY.toFloat(), 0f)

        val fixture = body.createFixture(shape, 0f)
        fixture.userData = "click"

        return true
    }

    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun scrolled(amount: Int): Boolean {
        return false
    }
}