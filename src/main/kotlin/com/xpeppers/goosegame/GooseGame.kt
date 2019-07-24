package com.xpeppers.goosegame

fun main() {
    val game = GooseGame(Players(), RealDiceRoller(), Printer())

    while (true) {
        print(game.execute(readLine()!!.toString()) + "\n")
    }
}

class GooseGame(private val players: Players, private val diceRoller: DiceRoller, private val printer: Printer) {

    fun execute(command: String): String {
        if (command.startsWith("move ")) {
            return movePlayer(command)
        }

        if (command.startsWith("add player")) {
            return addPlayers(command)
        }

        return printer.notFound()
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
