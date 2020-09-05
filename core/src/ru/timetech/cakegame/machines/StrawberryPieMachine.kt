package ru.timetech.cakegame.machines

import com.badlogic.gdx.graphics.g2d.Sprite
import ru.timetech.cakegame.defaults.Machine
import ru.timetech.cakegame.enums.PieType

class StrawberryPieMachine(var strawberryPieAsset: Sprite): Machine(){
    init {
        pies[PieType.STRAWBERRY] = strawberryPieAsset
    }

    override fun work() {
        spawnElement(PieType.STRAWBERRY)
    }
}