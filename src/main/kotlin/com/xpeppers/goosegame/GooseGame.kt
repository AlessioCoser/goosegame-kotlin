package com.xpeppers.goosegame

fun main() {
    val game = GooseGame(Players(), RealDiceRoller(), CliPrinter())

    while (true) {
        print(game.run(readLine()!!.toString()) + "\n")
    }
}

class GooseGame(private val players: Players, private val diceRoller: DiceRoller, private val printer: Printer) {

    fun run(command: String): String {
        return handlers()
            .first { handler -> handler.canHandle(command) }
            .handle(command)
    }

    private fun handlers(): List<Handler> {
        return listOf(
            MovePlayer(players, diceRoller, printer),
            AddPlayer(printer, players),
            NotFound(printer)
        )
    }
}
