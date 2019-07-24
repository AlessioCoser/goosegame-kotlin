package com.xpeppers.goosegame

class Printer {
    fun prank(other: Player): String =
        ". On ${printPosition(other.previousPosition)} there is ${other.name}, who returns to ${printPosition(other.position)}"

    fun theGoose(name: String, beginPosition: Int, landPosition: Int, dice: Dice, moves: MutableList<Int>): String =
        movePlayer(name, dice, beginPosition, landPosition) +
                moves.joinToString("") { move -> ", The Goose. $name moves again and goes to ${printPosition(move)}" }

    fun bridge(player: Player, dice: Dice, previousPosition: Int, bridgeSpace: Int): String =
        movePlayer(player.name, dice, previousPosition, bridgeSpace) +
                ". ${player.name} jumps to 12"

    fun win(player: Player, dice: Dice, previousPosition: Int): String =
        movePlayer(player.name, dice, previousPosition, player.position) +
                ". ${player.name} Wins!!"

    fun bounce(player: Player, dice: Dice, previousPosition: Int, winSpace: Int): String =
        movePlayer(player.name, dice, previousPosition, winSpace) +
                ". ${player.name} bounces! ${player.name} returns to ${player.position}"

    fun movePlayer(name: String, dice: Dice, previousPosition: Int, newPosition: Int): String =
        "$name rolls ${dice.first}, ${dice.second}. $name moves from ${printPosition(previousPosition)} to ${printPosition(newPosition)}"

    private fun printPosition(position: Int) = when (position) {
        0 -> "Start"
        6 -> "The Bridge"
        else -> position
    }

    fun notFound(): String = "Error: command not found"
}