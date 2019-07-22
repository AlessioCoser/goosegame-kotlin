package com.xpeppers.goosegame

class BridgePolicy(private val printer: Printer, val players: Players) {
    private val bridgeSpace = 6

    fun canExecute(player: Player): Boolean {
        return player.position == bridgeSpace
    }

    fun execute(player: Player, dice: Dice): String {
        val previousPosition = player.previousPosition
        players.updatePosition(player, 12)
        return printer.bridge(player, dice, previousPosition, bridgeSpace)
    }
}