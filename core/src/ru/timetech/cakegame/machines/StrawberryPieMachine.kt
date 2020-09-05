package ru.timetech.cakegame.machines

import com.badlogic.gdx.graphics.g2d.Sprite
import ru.timetech.cakegame.defaults.Machine
import ru.timetech.cakegame.enums.PieType

class StrawberryPieMachine(var strawberryPieAsset: Sprite): Machine(){
    init {
        pies[PieType.STRAWBERRY] = strawberryPieAsset
    }

    override fun work() {
        val pie = spawnElement(PieType.STRAWBERRY)
        pie.setPosition(sprite.x + sprite.width / 8, sprite.y - 38f)
    }
}