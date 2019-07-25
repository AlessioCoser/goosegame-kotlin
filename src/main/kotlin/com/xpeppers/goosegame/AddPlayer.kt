package com.xpeppers.goosegame

class AddPlayer(private val printer: Printer, private val players: Players): CommandHandler {

    override fun canHandle(command: Command): Boolean =
        command.type == Command.Type.ADD_PLAYER

    override fun handle(command: Command): String {
        if (players.exists(command.name)) {
            return printer.playerAlreadyExists(command.name)
        }

        players.add(command.name)

        return printer.players(players.names())
    }
}