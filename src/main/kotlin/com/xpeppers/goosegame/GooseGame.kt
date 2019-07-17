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
        players.add(command.substring(11))

        return "players: " + players.joinToString(", ")
    }
}
