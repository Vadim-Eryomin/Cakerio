package ru.timetech.cakegame.defaults

import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import ru.timetech.cakegame.CakeGame
import ru.timetech.cakegame.enums.CoffeeType
import ru.timetech.cakegame.enums.CreamType
import ru.timetech.cakegame.enums.PieType

open class Machine: Object(){
    lateinit var game: CakeGame
    val creams = HashMap<CreamType, Sprite>()
    val coffees = HashMap<CoffeeType, Sprite>()
    val pies = HashMap<PieType, Sprite>()

    fun create(sprite: Sprite, world: World, game: CakeGame) {
        super.create(null, sprite, world)
        this.shape = PolygonShape()
        this.world = world
        this.game = game
        (this.shape as PolygonShape).setAsBox(sprite.width / 2f, sprite.height / 2f)
        fixture.userData = "machine"

        game.machines.add(this)
    }

    open fun addAsset(type: CreamType, sprite: Sprite) = creams.put(type, sprite)
    open fun addAsset(type: CoffeeType, sprite: Sprite) = coffees.put(type, sprite)
    open fun addAsset(type: PieType, sprite: Sprite) = pies.put(type, sprite)

    open fun spawnElement(type: CreamType): Cream{
        val cream = Cream()
        creams.get(type)?.let { cream.create(cream.defaultCreamShape, it, type, world) }
        game.objects.add(cream)
        return cream
    }

    open fun spawnElement(type: CoffeeType): Coffee{
        val coffee = Coffee()
        coffees.get(type)?.let { coffee.create(it, type, world) }
        game.objects.add(coffee)
        return coffee
    }

    open fun spawnElement(type: PieType): Pie{
        val pie = Pie()
        pies.get(type)?.let { pie.create(null, it, type, world) }
        game.objects.add(pie)
        return pie
    }

    override fun onClick() {
        work()
    }

    open fun work(){
        // use in machines for spawn need element
    }
}