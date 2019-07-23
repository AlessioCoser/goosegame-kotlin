package com.xpeppers.goosegame

class Printer {
    fun theGoose(player: Player, response: String): String =
        response + ", The Goose. ${player.name} moves again and goes to ${player.position}"

    fun bridge(player: Player, dice: Dice, previousPosition: Int, bridgeSpace: Int) =
        movePlayer(player.name, dice, previousPosition, bridgeSpace) +
                ". ${player.name} jumps to 12"

    fun win(player: Player, dice: Dice, previousPosition: Int) =
        movePlayer(player.name, dice, previousPosition, player.position) +
                ". ${player.name} Wins!!"

    fun bounce(player: Player, dice: Dice, previousPosition: Int, winSpace: Int) =
        movePlayer(player.name, dice, previousPosition, winSpace) +
                ". ${player.name} bounces! ${player.name} returns to ${player.position}"

    fun movePlayer(name: String, dice: Dice, previousPosition: Int, newPosition: Int): String {
        return "$name rolls ${dice.first}, ${dice.second}. $name moves from ${printPosition(previousPosition)} to ${printPosition(newPosition)}"
    }

    private fun printPosition(position: Int): String {
        if (position == 0) {
            return "Start"
        }

        if (position == 6) {
            return "The Bridge"
        }

        return position.toString()
    }
}