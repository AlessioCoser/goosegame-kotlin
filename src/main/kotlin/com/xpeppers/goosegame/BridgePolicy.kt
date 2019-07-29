package com.xpeppers.goosegame

import com.xpeppers.goosegame.GameResponse.*

class BridgePolicy(val players: Players): Policy {
    private val bridgeSpace = 6
    private val jumpTo = 12

    override fun canExecute(player: Player): Boolean {
        return player.position == bridgeSpace
    }

    override fun execute(player: Player, dice: Dice): GameResponse {
        val previousPosition = player.previousPosition
        players.updatePosition(player, jumpTo)

        return BridgeResponse(player.name, previousPosition, bridgeSpace, dice)
    }
}