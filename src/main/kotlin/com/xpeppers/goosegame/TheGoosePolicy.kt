package com.xpeppers.goosegame

class TheGoosePolicy(private val printer: Printer, private val players: Players) : Policy {
    override fun canExecute(player: Player): Boolean {
        return player.position in listOf(5, 9, 14, 18, 23, 27)
    }

    override fun execute(player: Player, dice: Dice): String {
        val response = printer.movePlayer(player.name, dice, player.previousPosition, player.position)

        return moves(player, dice, response)
    }

    private fun moves(player: Player, dice: Dice, response: String): String {
        if (!canExecute(player)) {
            return response
        }

        players.updatePosition(player, player.position + dice.sum)

        return moves(player, dice, printer.theGoose(player, response))
    }
}