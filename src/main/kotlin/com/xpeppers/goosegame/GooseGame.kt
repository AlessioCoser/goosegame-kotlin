package com.xpeppers.goosegame

class GooseGame(private val players: Players, private val diceRoller: DiceRoller, private val printer: Printer) {

    fun run(command: Command): GameResponse {
        return handlers()
            .first { handler -> handler.canHandle(command) }
            .handle(command)
    }

    private fun handlers(): List<CommandHandler> {
        return listOf(
            MovePlayer(players, diceRoller, printer),
            AddPlayer(printer, players),
            NotFound(printer)
        )
    }
}
