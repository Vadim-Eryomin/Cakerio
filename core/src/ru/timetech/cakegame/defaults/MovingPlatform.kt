package ru.timetech.cakegame.defaults

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Fixture
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import ru.timetech.cakegame.CakeGame

class MovingPlatform: Object() {
    lateinit var game: CakeGame
    val movable = arrayOf("pie", "glass", "cream")
    val speed = 40f


    fun create(shape: PolygonShape?, sprite: Sprite, world: World, game: CakeGame) {
        super.create(shape, sprite, world)
        this.game = game
        this.shape = PolygonShape()
        (this.shape as PolygonShape).setAsBox(sprite.width / 2f, sprite.height / 2f)
        fixture.userData = "moving"
    }

    override fun update() {
        val contacts = world.contactList
        for (contact in contacts){
            if (contact.isTouching){
                val a = contact.fixtureA
                val b = contact.fixtureB

                val moving = if (a.userData == "moving") a else if (b.userData == "moving") b else null
                moving?.let {
                    var element: Object? = null
                    var fixture: Fixture? = null

                    movable.forEach {
                        fixture = if (a.userData == it) a else b
                        println(it)
                        println(fixture)
                    }
                    fixture?.let {
                        println("fixture")
                        game.objects.forEach {
                            if (fixture!!.body.hashCode() == it.body.hashCode())
                                element = it
                        }
                    }
                    element?.let {
                        element!!.body.linearVelocity = Vector2(speed,0f)
                        println("contact")
                    }
                }
            }
        }
    }
}