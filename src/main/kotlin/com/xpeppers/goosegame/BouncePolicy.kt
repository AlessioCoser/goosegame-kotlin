package com.xpeppers.goosegame

class BouncePolicy(private val printer: Printer, private val players: Players) {
    private val winSpace = 63

    fun canExecute(player: Player): Boolean {
        return player.position > winSpace
    }

    fun execute(player: Player, dice: Dice): String {
        val previousPosition = player.previousPosition
        players.updatePosition(player, winSpace - (player.position - winSpace))
        return printer.bounce(player, dice, previousPosition, 63)
    }
}