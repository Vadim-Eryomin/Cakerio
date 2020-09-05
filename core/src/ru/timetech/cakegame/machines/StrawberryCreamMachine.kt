package ru.timetech.cakegame.machines

import com.badlogic.gdx.graphics.g2d.Sprite
import ru.timetech.cakegame.defaults.Machine
import ru.timetech.cakegame.enums.CreamType

class StrawberryCreamMachine(var strawberryCreamAsset: Sprite) : Machine() {
    init {
        creams[CreamType.STRAWBERRY] = strawberryCreamAsset
    }

    override fun work() {
        spawnElement(CreamType.STRAWBERRY)
    }
}