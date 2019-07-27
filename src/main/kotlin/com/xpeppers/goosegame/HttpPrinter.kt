package com.xpeppers.goosegame

class HttpPrinter : Printer {
    override fun prank(name: String, beginPosition: Int, landPosition: Int, dice: Dice, other: Player) =
        GameResponse.ok(move(name, dice, beginPosition, landPosition, "${other.name} returns to ${other.position}!"))

    override fun theGoose(name: String, beginPosition: Int, landPosition: Int, dice: Dice, moves: MutableList<Int>) =
        GameResponse.ok(move(name, dice, beginPosition, moves.last(), "Moves again!"))

    override fun bridge(player: Player, dice: Dice, previousPosition: Int, bridgeSpace: Int) =
        GameResponse.ok(move(player.name, dice, previousPosition, player.position, "Jumps!"))

    override fun win(player: Player, dice: Dice, previousPosition: Int) =
        GameResponse.ok(move(player.name, dice, previousPosition, player.position, "Wins!"))

    override fun bounce(player: Player, dice: Dice, previousPosition: Int, winSpace: Int) =
        GameResponse.ok(move(player.name, dice, previousPosition, player.position, "Bounces!"))

    override fun movePlayer(name: String, dice: Dice, previousPosition: Int, newPosition: Int) =
        GameResponse.ok(move(name, dice, previousPosition, newPosition))

    override fun playerAlreadyExists(playerName: String) =
        GameResponse.alreadyExists("{\"alreadyExists\": \"$playerName: Already existing player\"}")

    override fun players(names: List<String>) =
        GameResponse.ok("{\"players\": [\"${names.joinToString("\", \"")}\"]}")

    override fun notFound() =
        GameResponse.notFound("")

    private fun move(name: String, dice: Dice, previousPosition: Int, currentPosition: Int, status: String = "") =
        "{\"$name\":{\"rolls\":[${dice.first},${dice.second}],\"moves\":{\"from\":\"${printPosition(previousPosition)}\",\"to\":\"${printPosition(currentPosition)}\"},\"status\":\"$status\"}}"

    private fun printPosition(position: Int): String = when (position) {
        0 -> "Start"
        else -> position.toString()
    }
}

