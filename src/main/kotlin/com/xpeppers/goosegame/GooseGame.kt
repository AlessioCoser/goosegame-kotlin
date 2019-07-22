package com.xpeppers.goosegame

fun main() {
    val game = GooseGame(Players(), RealDiceRoller())

    while (true) {
        print(game.execute(readLine()!!.toString()) + "\n")
    }
}

class GooseGame(
    private val players: Players,
    private val diceRoller: DiceRoller,
    private val printer: Printer = Printer()
) {
    private val winSpace = 63

    private val bridgeSpace = 6

    fun execute(command: String): String {
        if (command.startsWith("move ")) {
            return movePlayer(command)
        }

        return addPlayers(command)
    }

    private fun addPlayers(command: String): String {
        val playerName = command.substring(11)

        if (players.present(playerName)) {
            return "$playerName: already existing player"
        }

        players.add(playerName)

        return "players: " + players.names().joinToString(", ")
    }

    private fun movePlayer(command: String): String {
        val elements = command.split(" ", ",")
        val player = players.find(elements[1])
        val dice = diceRoller.roll()

        players.updatePosition(player, player.position + dice.sum)

        val winPolicy = WinPolicy(printer)
        val bouncePolicy = BouncePolicy(printer, players)
        val bridgePolicy = BridgePolicy(printer, players)
        val defaultPolicy = DefaultPolicy(printer)

        if (winPolicy.canExecute(player)) {
            return winPolicy.execute(player, dice)
        }

        if (bouncePolicy.canExecute(player)) {
            return bouncePolicy.execute(player, dice)
        }

        if (bridgePolicy.canExecute(player)) {
            return bridgePolicy.execute(player, dice)
        }

        if(defaultPolicy.canExecute(player)) {
            return defaultPolicy.execute(player, dice)
        }

        return ""
    }
}
