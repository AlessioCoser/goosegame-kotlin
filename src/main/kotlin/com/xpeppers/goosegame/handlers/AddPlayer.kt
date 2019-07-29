package com.xpeppers.goosegame.handlers

import com.xpeppers.goosegame.Command
import com.xpeppers.goosegame.GameResponse
import com.xpeppers.goosegame.GameResponse.*
import com.xpeppers.goosegame.Players

class AddPlayer(private val players: Players): CommandHandler {

    override fun canHandle(command: Command): Boolean =
        command.type == Command.Type.ADD_PLAYER

    override fun handle(command: Command): GameResponse {
        if (players.exists(command.name)) {
            return AlreadyExistsResponse(command.name)
        }

        players.add(command.name)

        return PlayersResponse(players.names())
    }
}