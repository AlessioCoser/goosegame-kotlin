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

        if (player.position > 63) {
            player.position = 63 - (player.position - 63)
            return printMovePlayer(player.name, diceOne, diceTwo, previousPosition, 63) +
                    ". ${player.name} bounces! ${player.name} returns to ${player.position}"
        }

        if (player.position == winSpace) {
            return printMovePlayer(player.name, diceOne, diceTwo, previousPosition, player.position) +
                    ". ${player.name} Wins!!"
        }

        return printMovePlayer(player.name, diceOne, diceTwo, previousPosition, player.position)
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

    private fun printMovePlayer(name: String, diceOne: Int, diceTwo: Int, previousPosition: Int, newPosition: Int): String {
        return "$name rolls $diceOne, $diceTwo. $name moves from ${printPosition(previousPosition)} to $newPosition"
    }

    private fun printPosition(position: Int) = if (position == 0) "Start" else position.toString()
}
