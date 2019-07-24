package com.xpeppers.goosegame

fun main() {
    val game = GooseGame(Players(), RealDiceRoller(), Printer())

    while (true) {
        print(game.run(readLine()!!.toString()) + "\n")
    }
}

class GooseGame(private val players: Players, private val diceRoller: DiceRoller, private val printer: Printer) {

    fun run(command: String): String {
        return commands()
            .first { c -> c.canRun(command) }
            .run(command)
    }

    private fun commands(): List<GameCommand> {
        return listOf(
            MovePlayerCommand(players, diceRoller, printer),
            AddPlayerCommand(printer, players),
            NotFoundCommand(printer)
        )
    }
}
