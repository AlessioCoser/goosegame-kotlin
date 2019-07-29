package com.xpeppers.goosegame

import com.xpeppers.goosegame.GameResponse.*

class DefaultPolicy : Policy {

    override fun canExecute(player: Player): Boolean {
        return true
    }

    override fun execute(player: Player, dice: Dice): GameResponse {
        return NormalResponse(player.name, player.previousPosition, player.position, dice)
    }
}