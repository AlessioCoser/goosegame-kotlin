package com.xpeppers.goosegame.policies

import com.xpeppers.goosegame.Dice
import com.xpeppers.goosegame.GameResponse
import com.xpeppers.goosegame.GameResponse.*
import com.xpeppers.goosegame.Player

class DefaultPolicy : Policy {

    override fun canExecute(player: Player): Boolean {
        return true
    }

    override fun execute(player: Player, dice: Dice): GameResponse {
        return NormalResponse(player.name, player.previousPosition, player.position, dice)
    }
}