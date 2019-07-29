package com.xpeppers.goosegame

import com.xpeppers.goosegame.handlers.AddPlayer
import com.xpeppers.goosegame.handlers.CommandHandler
import com.xpeppers.goosegame.handlers.MovePlayer
import com.xpeppers.goosegame.handlers.NotFound

class GooseGame(private val players: Players, private val diceRoller: DiceRoller) {
    fun run(command: Command): GameResponse {
        return handlers()
            .first { handler -> handler.canHandle(command) }
            .handle(command)
    }

    private fun handlers(): List<CommandHandler> {
        return listOf(
            MovePlayer(players, diceRoller),
            AddPlayer(players),
            NotFound()
        )
    }
}
