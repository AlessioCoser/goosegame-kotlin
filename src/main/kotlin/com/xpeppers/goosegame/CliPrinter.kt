package com.xpeppers.goosegame

import com.xpeppers.goosegame.GameResponse.*

class CliPrinter: Printer {
    override fun print(response: GameResponse): String = when(response) {
        is PrankResponse ->
            move(response.name, response.dice, response.beginPosition, response.landPosition) +
            ". On ${printPosition(response.other.previousPosition)} there is ${response.other.name}, who returns to ${printPosition(response.other.position)}\n"
        is GooseResponse ->
            move(response.name, response.dice, response.beginPosition, response.landPosition) +
            response.moves.joinToString("") { move -> ", The Goose. ${response.name} moves again and goes to ${printPosition(move)}" } + "\n"
        is BridgeResponse ->
            move(response.name, response.dice, response.beginPosition, response.landPosition) + ". ${response.name} jumps to 12\n"
        is WinResponse ->
            move(response.name, response.dice, response.beginPosition, response.landPosition) + ". ${response.name} Wins!!\n"
        is BounceResponse ->
            move(response.name, response.dice, response.beginPosition, response.winSpace) +
            ". ${response.name} bounces! ${response.name} returns to ${response.landPosition}\n"
        is NormalResponse ->
            move(response.name, response.dice, response.beginPosition, response.landPosition) + "\n"
        is AlreadyExistsResponse ->
            "${response.name}: already existing player\n"
        is PlayersResponse ->
            "players: " + response.names.joinToString(", ") + "\n"
        is NotFoundResponse ->
            "Error: command not found\n"
    }

    private fun move(name: String, dice: Dice, previousPosition: Int, newPosition: Int) =
        "$name rolls ${dice.first}, ${dice.second}. $name moves from ${printPosition(previousPosition)} to ${printPosition(newPosition)}"

    private fun printPosition(position: Int): String = when (position) {
        0 -> "Start"
        6 -> "The Bridge"
        else -> position.toString()
    }
}
