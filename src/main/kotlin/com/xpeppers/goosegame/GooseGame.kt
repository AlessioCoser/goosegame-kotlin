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
        val playerName = command.substring(11)

        if (players.contains(playerName)) {
            return "$playerName: already existing player"
        }

        players.add(playerName)

        return "players: " + players.joinToString(", ")
    }
}
