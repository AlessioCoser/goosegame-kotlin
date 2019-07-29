package com.xpeppers.goosegame

import com.xpeppers.goosegame.GameResponse.*

class CliPrinter: Printer {
    override fun print(response: GameResponse): String = when(response) {
        is PrankResponse -> prank(response)
        is GooseResponse -> goose(response)
        is BridgeResponse -> bridge(response)
        is WinResponse -> win(response)
        is BounceResponse -> bounce(response)
        is NormalResponse -> normal(response)
        is AlreadyExistsResponse -> "${response.name}: already existing player\n"
        is PlayersResponse -> "players: ${join(response.names)}\n"
        is NotFoundResponse -> "Error: command not found\n"
    }

    private fun normal(response: NormalResponse) =
        move(response.name, response.dice, response.beginPosition, response.landPosition) + "\n"

    private fun bounce(response: BounceResponse) =
        move(response.name, response.dice, response.beginPosition, response.winSpace) +
        ". ${response.name} bounces! ${response.name} returns to ${response.landPosition}\n"

    private fun win(response: WinResponse) =
        move(response.name, response.dice, response.beginPosition, response.landPosition) + ". ${response.name} Wins!!\n"

    private fun bridge(response: BridgeResponse) =
        move(response.name, response.dice, response.beginPosition, response.landPosition) + ". ${response.name} jumps to 12\n"

    private fun goose(response: GooseResponse) =
        move(response.name, response.dice, response.beginPosition, response.landPosition) +
        response.moves.joinToString("") { move ->
            ", The Goose. ${response.name} moves again and goes to ${printPosition(move)}"
        } + "\n"

    private fun prank(response: PrankResponse) =
        move(response.name, response.dice, response.beginPosition, response.landPosition) +
        ". On ${printPosition(response.other.previousPosition)} there is ${response.other.name}, who returns to ${printPosition(response.other.position)}\n"

    private fun move(name: String, dice: Dice, previousPosition: Int, newPosition: Int) =
        "$name rolls ${dice.first}, ${dice.second}. $name moves from ${printPosition(previousPosition)} to ${printPosition(newPosition)}"

    private fun printPosition(position: Int): String = when (position) {
        0 -> "Start"
        6 -> "The Bridge"
        else -> position.toString()
    }

    private fun join(names: List<String>) = names.joinToString(", ")
}
