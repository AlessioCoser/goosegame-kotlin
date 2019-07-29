package com.xpeppers.goosegame

import com.xpeppers.goosegame.GameResponse.*

class HttpPrinter : Printer {
    override fun print(response: GameResponse): String = when(response) {
        is PrankResponse -> move(response.name, response.dice, response.beginPosition, response.landPosition, "${response.other.name} returns to ${response.other.position}!")
        is GooseResponse -> move(response.name, response.dice, response.beginPosition, response.moves.last(), "Moves again!")
        is BridgeResponse -> move(response.name, response.dice, response.beginPosition, response.landPosition + response.dice.sum, "Jumps!")
        is WinResponse -> move(response.name, response.dice, response.beginPosition, response.landPosition, "Wins!")
        is BounceResponse -> move(response.name, response.dice, response.beginPosition, response.landPosition, "Bounces!")
        is NormalResponse -> move(response.name, response.dice, response.beginPosition, response.landPosition)
        is AlreadyExistsResponse -> "{\"alreadyExists\": \"${response.name}: Already existing player\"}"
        is PlayersResponse -> "{\"players\": [\"${response.names.joinToString("\", \"")}\"]}"
        is NotFoundResponse -> ""
    }

    private fun move(name: String, dice: Dice, previousPosition: Int, currentPosition: Int, status: String = "") =
        "{\"$name\":{\"rolls\":[${dice.first},${dice.second}],\"moves\":{\"from\":\"${printPosition(previousPosition)}\",\"to\":\"${printPosition(currentPosition)}\"},\"status\":\"$status\"}}"

    private fun printPosition(position: Int): String = when (position) {
        0 -> "Start"
        else -> position.toString()
    }
}

