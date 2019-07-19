package com.xpeppers.goosegame

fun main() {
    val game = GooseGame(Players())

    while (true) {
        print(game.execute(readLine()!!.toString()) + "\n")
    }
}

class GooseGame(private val players: Players) {
    private val winSpace = 63

    fun execute(command: String): String {
        if (command.startsWith("move ")) {
            return movePlayer(command)
        }

        return addPlayers(command)
    }

    private fun movePlayer(command: String): String {
        val elements = command.split(" ", ",")
        val player = players.find(elements[1])
        val dice = Dice(elements[2].toInt(), elements[4].toInt())

        val previousPosition = player.position
        players.updatePosition(player, previousPosition + dice.sum)

        if (player.position > winSpace) {
            players.updatePosition(player, winSpace - (player.position - winSpace))
            return printMovePlayer(player.name, dice, previousPosition, winSpace) +
                    ". ${player.name} bounces! ${player.name} returns to ${player.position}"
        }

        if (player.position == winSpace) {
            return printMovePlayer(player.name, dice, previousPosition, player.position) +
                    ". ${player.name} Wins!!"
        }

        return printMovePlayer(player.name, dice, previousPosition, player.position)
    }

    private fun addPlayers(command: String): String {
        val playerName = command.substring(11)

        if (players.present(playerName)) {
            return "$playerName: already existing player"
        }

        players.add(playerName)

        return "players: " + players.names().joinToString(", ")
    }

    private fun printMovePlayer(name: String, dice: Dice, previousPosition: Int, newPosition: Int): String {
        return "$name rolls ${dice.first}, ${dice.second}. $name moves from ${printPosition(previousPosition)} to $newPosition"
    }

    private fun printPosition(position: Int) = if (position == 0) "Start" else position.toString()
}
