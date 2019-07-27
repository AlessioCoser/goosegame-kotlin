package com.xpeppers.goosegame

class WinPolicy(private val printer: Printer): Policy {
    private val winSpace = 63

    override fun canExecute(player: Player): Boolean {
        return player.position == winSpace
    }

    override fun execute(player: Player, dice: Dice): GameResponse {
        return printer.win(player, dice, player.previousPosition)
    }
}