package com.xpeppers.goosegame

class CliPrinter(): Printer {

    override fun prank(name: String, beginPosition: Int, landPosition: Int, dice: Dice, other: Player) =
        GameResponse.ok(move(name, dice, beginPosition, landPosition) +
                ". On ${printPosition(other.previousPosition)} there is ${other.name}, who returns to ${printPosition(other.position)}")

    override fun theGoose(name: String, beginPosition: Int, landPosition: Int, dice: Dice, moves: MutableList<Int>) =
        GameResponse.ok(move(name, dice, beginPosition, landPosition) +
                moves.joinToString("") { move -> ", The Goose. $name moves again and goes to ${printPosition(move)}" })

    override fun bridge(player: Player, dice: Dice, previousPosition: Int, bridgeSpace: Int) =
        GameResponse.ok(move(player.name, dice, previousPosition, bridgeSpace) +
                ". ${player.name} jumps to 12")

    override fun win(player: Player, dice: Dice, previousPosition: Int) =
        GameResponse.ok(move(player.name, dice, previousPosition, player.position) +
                ". ${player.name} Wins!!")

    override fun bounce(player: Player, dice: Dice, previousPosition: Int, winSpace: Int) =
        GameResponse.ok(move(player.name, dice, previousPosition, winSpace) + ". ${player.name} bounces! ${player.name} returns to ${player.position}")

    override fun movePlayer(name: String, dice: Dice, previousPosition: Int, newPosition: Int) =
        GameResponse.ok(move(name, dice, previousPosition, newPosition))

    override fun playerAlreadyExists(playerName: String) = GameResponse.ok("$playerName: already existing player")

    override fun players(names: List<String>) = GameResponse.ok("players: " + names.joinToString(", "))

    override fun notFound() = GameResponse.ok("Error: command not found")

    private fun move(name: String, dice: Dice, previousPosition: Int, newPosition: Int) =
        "$name rolls ${dice.first}, ${dice.second}. $name moves from ${printPosition(previousPosition)} to ${printPosition(newPosition)}"

    private fun printPosition(position: Int) = when (position) {
        0 -> "Start"
        6 -> "The Bridge"
        else -> position
    }
}
