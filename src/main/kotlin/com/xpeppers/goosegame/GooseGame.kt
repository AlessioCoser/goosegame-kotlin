package com.xpeppers.goosegame

fun main() {
    val game = GooseGame(Players(), RealDiceRoller(), Printer())

    while (true) {
        print(game.execute(readLine()!!.toString()) + "\n")
    }
}

class GooseGame(private val players: Players, private val diceRoller: DiceRoller, private val printer: Printer) {

    fun execute(command: String): String {
        if (isMovePlayerCommand(command)) {
            return movePlayerCommand(command)
        }

        val addPlayer = AddPlayerCommand(printer, players)

        if (addPlayer.canRun(command)) {
            return addPlayer.run(command)
        }

        val notFound = NotFoundCommand(printer)

        if(notFound.canRun(command)) {
            return notFound.run(command)
        }

        return ""
    }

    private fun isAddPlayerCommand(command: String) = command.startsWith("add player")

    private fun addPlayerCommand(command: String): String {
        val playerName = command.substring(11)

        if (players.present(playerName)) {
            return "$playerName: already existing player"
        }

        players.add(playerName)

        return "players: " + players.names().joinToString(", ")
    }

    private fun isMovePlayerCommand(command: String) = command.startsWith("move ")

    private fun movePlayerCommand(command: String): String {
        val elements = command.split(" ", ",")
        val player = players.find(elements[1])
        val dice = diceRoller.roll()

        players.updatePosition(player, player.position + dice.sum)

        return policies()
            .first { policy -> policy.canExecute(player) }
            .execute(player, dice)
    }

    private fun policies() = listOf(
        WinPolicy(printer),
        BouncePolicy(printer, players),
        BridgePolicy(printer, players),
        TheGoosePolicy(printer, players),
        PrankPolicy(printer, players),
        DefaultPolicy(printer)
    )
}
