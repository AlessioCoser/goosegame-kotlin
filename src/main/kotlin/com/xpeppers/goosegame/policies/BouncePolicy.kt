package com.xpeppers.goosegame.policies

import com.xpeppers.goosegame.*
import com.xpeppers.goosegame.GameResponse.*

class BouncePolicy(private val players: Players): Policy {
    private val winSpace = 63

    override fun canExecute(player: Player): Boolean {
        return player.position > winSpace
    }

    override fun execute(player: Player, dice: Dice): GameResponse {
        val previousPosition = player.previousPosition
        players.updatePosition(player, winSpace - (player.position - winSpace))

        return BounceResponse(player.name, previousPosition, player.position, dice, winSpace)
    }
}