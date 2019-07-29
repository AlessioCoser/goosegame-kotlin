package com.xpeppers.goosegame.policies

import com.xpeppers.goosegame.Dice
import com.xpeppers.goosegame.GameResponse
import com.xpeppers.goosegame.Player
import com.xpeppers.goosegame.Players

class PrankPolicy(private val players: Players) : Policy {
    override fun canExecute(player: Player): Boolean {
        return players.inSamePositionOf(player).isNotEmpty()
    }

    override fun execute(player: Player, dice: Dice): GameResponse {
        val other = players.inSamePositionOf(player).single()

        players.updatePosition(other, player.previousPosition)

        return GameResponse.PrankResponse(
            player.name,
            player.previousPosition,
            player.position,
            dice,
            other
        )
    }
}