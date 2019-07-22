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

        if (isWinPolicy(player)) {
            return winPolicy(player, dice)
        }

        if (isBouncePolicy(player)) {
            return bouncePolicy(player, dice)
        }

        if (isBridgePolicy(player)) {
            return bridgePolicy(player, dice)
        }

        return defaultPolicy(player, dice)
    }

    private fun isWinPolicy(player: Player) = player.position == winSpace

    private fun winPolicy(player: Player, dice: Dice): String {
        return printer.win(player, dice, player.previousPosition)
    }

    private fun isBridgePolicy(player: Player) = player.position == bridgeSpace

    private fun bridgePolicy(player: Player, dice: Dice): String {
        val previousPosition = player.previousPosition
        players.updatePosition(player, 12)
        return printer.bridge(player, dice, previousPosition, bridgeSpace)
    }

    private fun isBouncePolicy(player: Player) = player.position > winSpace

    private fun bouncePolicy(player: Player, dice: Dice): String {
        val previousPosition = player.previousPosition
        players.updatePosition(player, winSpace - (player.position - winSpace))
        return printer.bounce(player, dice, previousPosition, 63)
    }

    private fun defaultPolicy(player: Player, dice: Dice) =
        printer.movePlayer(player.name, dice, player.previousPosition, player.position)
}
