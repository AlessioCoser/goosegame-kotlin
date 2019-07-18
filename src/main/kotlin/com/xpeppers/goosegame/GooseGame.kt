package com.xpeppers.goosegame

fun main() {
    val game = GooseGame()

    while (true) {
        print(game.execute(readLine()!!.toString()) + "\n")
    }
}

class GooseGame {
    private val players = mutableListOf<Player>()

    fun execute(command: String): String {
        if (command.startsWith("move ")) {
            return movePlayer(command)
        }

        return addPlayers(command)
    }

    private fun movePlayer(command: String): String {
        val elements = command.split(" ", ",")
        val player = findPlayerBy(elements[1])
        val diceOne = elements[2].toInt()
        val diceTwo = elements[4].toInt()

        val previousPosition = player.position
        player.position = previousPosition + diceOne + diceTwo

        return "${player.name} rolls $diceOne, $diceTwo. " +
                "${player.name} moves from ${printPosition(previousPosition)}" +
                " to ${player.position}"
    }

    private fun addPlayers(command: String): String {
        val playerName = command.substring(11)

        if (playerNames().contains(playerName)) {
            return "$playerName: already existing player"
        }

        players.add(Player(playerName))

        return "players: " + playerNames().joinToString(", ")
    }

    private fun findPlayerBy(name: String) = players.first { player -> player.name == name }

    private fun playerNames() = players.map(Player::name)

    private fun printPosition(position: Int) = if (position == 0) "Start" else position.toString()
}
