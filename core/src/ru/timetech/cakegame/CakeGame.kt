package ru.timetech.cakegame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g3d.model.MeshPart
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.BoxShapeBuilder
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.utils.Array
import ru.timetech.cakegame.defaults.Machine
import ru.timetech.cakegame.defaults.MovingPlatform
import ru.timetech.cakegame.enums.PieType
import ru.timetech.cakegame.listeners.InputListener
import ru.timetech.cakegame.machines.StrawberryPieMachine

class CakeGame: MyWorld(){
    lateinit var machine: Machine
    lateinit var machineTexture: Texture
    lateinit var platform: MovingPlatform
    override fun create() {
        super.create()
        Gdx.input.inputProcessor = InputListener(this)
        machineTexture = Texture(Gdx.files.internal("machine.bmp"))

        machine = StrawberryPieMachine(Sprite(Texture(Gdx.files.internal("strawberryPie.bmp"))))
        machine.create(Sprite(machineTexture), world, this)
        machine.setPosition(500f, 500f)

        platform = MovingPlatform()
        platform.create(null, Sprite(Texture(Gdx.files.internal("transporter.bmp"))), world, this)
        platform.setPosition(500f, 100f)
    }

    override fun render() {
        super.render()
        platform.update()
        batch.begin()
        machine.render(batch)
        objects.forEach {
            it.render(batch)
        }
        platform.render(batch)
        batch.end()
    }

    override fun resize(width: Int, height: Int) {
        camera.setToOrtho(true, width.toFloat(), height.toFloat())
    }

    override fun dispose() {
        super.dispose()
    }
}