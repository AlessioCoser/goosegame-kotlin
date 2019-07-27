package com.xpeppers.goosegame

class HttpPrinter : Printer {
    override fun prank(name: String, beginPosition: Int, landPosition: Int, dice: Dice, other: Player): GameResponse {
        return GameResponse.ok("{\"$name\":{\"rolls\":[${dice.first},${dice.second}],\"moves\":{\"from\":\"${printPosition(beginPosition)}\",\"to\":\"${printPosition(landPosition)}\"},\"status\":\"${other.name} returns to ${other.position}!\"}}")
    }

    override fun theGoose(name: String, beginPosition: Int, landPosition: Int, dice: Dice, moves: MutableList<Int>): GameResponse {
        return GameResponse.ok("{\"$name\":{\"rolls\":[${dice.first},${dice.second}],\"moves\":{\"from\":\"${printPosition(beginPosition)}\",\"to\":\"${printPosition(moves.last())}\"},\"status\":\"Moves again!\"}}")
    }

    override fun bridge(player: Player, dice: Dice, previousPosition: Int, bridgeSpace: Int): GameResponse {
        return GameResponse.ok("{\"${player.name}\":{\"rolls\":[${dice.first},${dice.second}],\"moves\":{\"from\":\"${printPosition(previousPosition)}\",\"to\":\"${printPosition(player.position)}\"},\"status\":\"Jumps!\"}}")
    }

    override fun win(player: Player, dice: Dice, previousPosition: Int): GameResponse {
        return GameResponse.ok("{\"${player.name}\":{\"rolls\":[${dice.first},${dice.second}],\"moves\":{\"from\":\"${printPosition(previousPosition)}\",\"to\":\"${printPosition(player.position)}\"},\"status\":\"Wins!\"}}")
    }

    override fun bounce(player: Player, dice: Dice, previousPosition: Int, winSpace: Int): GameResponse {
        return GameResponse.ok("{\"${player.name}\":{\"rolls\":[${dice.first},${dice.second}],\"moves\":{\"from\":\"${printPosition(previousPosition)}\",\"to\":\"${printPosition(player.position)}\"},\"status\":\"Bounces!\"}}")
    }

    override fun movePlayer(name: String, dice: Dice, previousPosition: Int, newPosition: Int): GameResponse {
        return GameResponse.ok("{\"$name\":{\"rolls\":[${dice.first},${dice.second}],\"moves\":{\"from\":\"${printPosition(previousPosition)}\",\"to\":\"${printPosition(newPosition)}\"},\"status\":\"\"}}")
    }

    override fun playerAlreadyExists(playerName: String): GameResponse {
        return GameResponse.alreadyExists("{\"alreadyExists\": \"$playerName: Already existing player\"}")
    }

    override fun players(names: List<String>): GameResponse {
        return GameResponse.ok("{\"players\": [\"${names.joinToString("\", \"")}\"]}")
    }

    override fun notFound(): GameResponse {
        return GameResponse.notFound("")
    }

    private fun printPosition(position: Int): String = when (position) {
        0 -> "Start"
        else -> position.toString()
    }
}

