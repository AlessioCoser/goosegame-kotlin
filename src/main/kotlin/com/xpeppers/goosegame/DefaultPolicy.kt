package com.xpeppers.goosegame

class DefaultPolicy(private val printer: Printer): Policy {

    override fun canExecute(player: Player): Boolean {
        return true
    }

    override fun execute(player: Player, dice: Dice): String {
        return printer.movePlayer(player.name, dice, player.previousPosition, player.position)
    }
}