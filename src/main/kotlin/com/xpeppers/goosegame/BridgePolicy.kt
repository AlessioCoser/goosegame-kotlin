package com.xpeppers.goosegame

class BridgePolicy(private val printer: Printer, val players: Players): Policy {
    private val bridgeSpace = 6
    private val jumpTo = 12

    override fun canExecute(player: Player): Boolean {
        return player.position == bridgeSpace
    }

    override fun execute(player: Player, dice: Dice): GameResponse {
        val previousPosition = player.previousPosition
        players.updatePosition(player, jumpTo)
        return printer.bridge(player, dice, previousPosition, bridgeSpace)
    }
}