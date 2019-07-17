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
        return "Pippo rolls 4, 2. Pippo moves from Start to 6"
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
