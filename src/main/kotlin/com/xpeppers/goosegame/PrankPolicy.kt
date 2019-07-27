package com.xpeppers.goosegame

class PrankPolicy(private val printer: Printer, private val players: Players) : Policy {
    override fun canExecute(player: Player): Boolean {
        return players.inSamePositionOf(player).isNotEmpty()
    }

    override fun execute(player: Player, dice: Dice): GameResponse {
        val other = players.inSamePositionOf(player).single()

        players.updatePosition(other, player.previousPosition)

        return printer.prank(player.name, player.previousPosition, player.position, dice, other)
    }
}