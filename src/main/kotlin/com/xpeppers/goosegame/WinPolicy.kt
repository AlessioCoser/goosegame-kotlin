package com.xpeppers.goosegame

class WinPolicy(private val printer: Printer) {
    private val winSpace = 63

    fun canExecute(player: Player): Boolean {
        return player.position == winSpace
    }

    fun execute(player: Player, dice: Dice): String {
        return printer.win(player, dice, player.previousPosition)
    }
}