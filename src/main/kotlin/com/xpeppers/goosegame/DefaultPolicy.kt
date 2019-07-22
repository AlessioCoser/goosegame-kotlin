package com.xpeppers.goosegame

class DefaultPolicy(private val printer: Printer) {

    fun canExecute(player: Player): Boolean {
        return true
    }

    fun execute(player: Player, dice: Dice): String {
        return printer.movePlayer(player.name, dice, player.previousPosition, player.position)
    }
}