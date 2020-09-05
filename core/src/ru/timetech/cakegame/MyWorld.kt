package ru.timetech.cakegame

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.utils.Array
import ru.timetech.cakegame.defaults.Machine
import ru.timetech.cakegame.defaults.Object
import ru.timetech.cakegame.defaults.Pie
import kotlin.system.exitProcess

open class MyWorld : ApplicationAdapter() {
    lateinit var batch: SpriteBatch
    lateinit var camera: OrthographicCamera
    lateinit var box2DDebugRenderer: Box2DDebugRenderer
    lateinit var world: World
    lateinit var machines: Array<Machine>
    lateinit var objects: Array<Object>

    override fun create() {
        machines = Array()
        objects = Array()
        batch = SpriteBatch()
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        box2DDebugRenderer = Box2DDebugRenderer()
        world = World(Vector2(0f, -100f), true)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        world.step(Gdx.graphics.deltaTime, 4, 4)
        box2DDebugRenderer.render(world, camera.combined)
        checkClick(world.contactList)
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit()
            exitProcess(0)
        }
        val bodies = Array<Body>()
        world.getBodies(bodies)
        bodies.forEach {
            if (it.fixtureList[0].userData == "click") {
                it.destroyFixture(it.fixtureList[0])
                world.destroyBody(it)
            }
        }

    }

    override fun dispose() {
        batch.dispose()
    }

    private fun checkClick(contacts: Array<Contact>){
        for (contact in contacts){
            if ((contact.fixtureA.userData != null && contact.fixtureA.userData == "machine") ||
                    (contact.fixtureB.userData != null && contact.fixtureB.userData == "machine")){
                if ((contact.fixtureA.userData != null && contact.fixtureA.userData == "click") ||
                        (contact.fixtureB.userData != null && contact.fixtureB.userData == "click")){
                    val needMachineBody =
                            (if (contact.fixtureA.userData == "machine") contact.fixtureA else contact.fixtureB).body
                    val clickBody =
                            (if (contact.fixtureA.userData == "click") contact.fixtureA else contact.fixtureB).body
                    for (machine in machines){
                        if (machine.body.hashCode() == needMachineBody.hashCode()){
                            machine.onClick()
                            clickBody.setTransform(1000f, 1000f, 0f)
                        }
                    }
                }
            }
        }
    }
}