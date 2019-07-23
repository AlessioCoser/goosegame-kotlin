package com.xpeppers.goosegame

class PrankPolicy(private val printer: Printer, private val players: Players) : Policy {
    override fun canExecute(player: Player): Boolean {
        return players.inSamePositionOf(player).isNotEmpty()
    }

    override fun execute(player: Player, dice: Dice): String {
        val other = players.inSamePositionOf(player).single()

        players.updatePosition(other, player.previousPosition)

        return printer.movePlayer(player.name, dice, player.previousPosition, player.position) +
                printer.prank(other)
    }
}