package com.xpeppers.goosegame

class TheGoosePolicy(private val players: Players) : Policy {
    override fun canExecute(player: Player): Boolean {
        return player.position in listOf(5, 9, 14, 18, 23, 27)
    }

    override fun execute(player: Player, dice: Dice): GameResponse {
        return GameResponse.GooseResponse(player.name, player.previousPosition, player.position, dice, moves(player, dice))
    }

    private fun moves(player: Player, dice: Dice): MutableList<Int> {
        val moves = mutableListOf<Int>()

        while (canExecute(player)) {
            players.updatePosition(player, player.position + dice.sum)
            moves.add(player.position)
        }

        return moves
    }
}