package com.xpeppers.goosegame

class TheGoosePolicy(private val printer: Printer, private val players: Players) : Policy {
    override fun canExecute(player: Player): Boolean {
        return player.position == 5
    }

    override fun execute(player: Player, dice: Dice): String {
        val beforeMovePosition = player.previousPosition
        players.updatePosition(player, player.position + dice.sum)
        return printer.theGoose(player, dice, beforeMovePosition)
    }
}