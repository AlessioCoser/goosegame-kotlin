package com.xpeppers.goosegame.policies

import com.xpeppers.goosegame.Dice
import com.xpeppers.goosegame.GameResponse
import com.xpeppers.goosegame.Player

interface Policy {
    fun canExecute(player: Player): Boolean
    fun execute(player: Player, dice: Dice): GameResponse
}
