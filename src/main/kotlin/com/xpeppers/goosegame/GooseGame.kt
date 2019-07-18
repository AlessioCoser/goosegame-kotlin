package com.xpeppers.goosegame

fun main() {
    val game = GooseGame()

    while (true) {
        print(game.execute(readLine()!!.toString()) + "\n")
    }
}

class GooseGame {
    private val winSpace = 63
    private val players = mutableListOf<Player>()

    fun execute(command: String): String {
        if (command.startsWith("move ")) {
            return movePlayer(command)
        }

        return addPlayers(command)
    }

    private fun movePlayer(command: String): String {
        val elements = command.split(" ", ",")
        val player = playersFind(elements[1])
        val diceOne = elements[2].toInt()
        val diceTwo = elements[4].toInt()

        val previousPosition = player.position
        player.position = previousPosition + diceOne + diceTwo

        return printMovePlayer(player, previousPosition, diceOne, diceTwo) + printPlayerWins(player, winSpace)
    }

    private fun addPlayers(command: String): String {
        val playerName = command.substring(11)

        if (playersPresent(playerName)) {
            return "$playerName: already existing player"
        }

        playersAdd(playerName)

        return "players: " + playersNames().joinToString(", ")
    }

    private fun playersAdd(playerName: String) = players.add(Player(playerName))

    private fun playersFind(name: String) = players.first { player -> player.name == name }

    private fun playersPresent(playerName: String) = playersNames().contains(playerName)

    private fun playersNames() = players.map(Player::name)

    private fun printPlayerWins(player: Player, winSpace: Int): String {
        if (player.position == winSpace) {
            return ". ${player.name} Wins!!"
        }

        return ""
    }

    private fun printMovePlayer(player: Player, previousPosition: Int, diceOne: Int, diceTwo: Int): String {
        return "${player.name} rolls $diceOne, $diceTwo. " +
                "${player.name} moves from ${printPosition(previousPosition)}" +
                " to ${player.position}"
    }

    private fun printPosition(position: Int) = if (position == 0) "Start" else position.toString()
}
