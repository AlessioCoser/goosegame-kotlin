package com.xpeppers.goosegame

class BouncePolicy(private val printer: Printer, private val players: Players): Policy {
    private val winSpace = 63

    override fun canExecute(player: Player): Boolean {
        return player.position > winSpace
    }

    override fun execute(player: Player, dice: Dice): GameResponse {
        val previousPosition = player.previousPosition
        players.updatePosition(player, winSpace - (player.position - winSpace))
        return printer.bounce(player, dice, previousPosition, winSpace)
    }
}