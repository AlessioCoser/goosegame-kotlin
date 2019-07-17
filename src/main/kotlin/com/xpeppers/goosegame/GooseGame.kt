package com.xpeppers.goosegame

fun main() {
    val game = GooseGame()

    while (true) {
        print(game.execute(readLine()!!.toString()) + "\n")
    }
}

class GooseGame {
    private val players = mutableListOf<String>()

    fun execute(command: String): String {
        if (command.startsWith("move ")) {
            return movePlayer(command)
        }

        return addPlayers(command)
    }

    private fun movePlayer(command: String): String {
        val elements = command.split(" ", ",")
        val playerName = elements[1]
        val diceOne = elements[2].toInt()
        val diceTwo = elements[4].toInt()
        val previousPosition = "Start"
        val newPosition = diceOne + diceTwo

        return "$playerName rolls $diceOne, $diceTwo. $playerName moves from $previousPosition to $newPosition"
    }

    private fun addPlayers(command: String): String {
        val playerName = command.substring(11)

        if (players.contains(playerName)) {
            return "$playerName: already existing player"
        }

        players.add(playerName)

        return "players: " + players.joinToString(", ")
    }
}
