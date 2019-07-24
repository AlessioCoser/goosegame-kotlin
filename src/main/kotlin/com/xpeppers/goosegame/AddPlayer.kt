package com.xpeppers.goosegame

class AddPlayer(private val printer: Printer, private val players: Players): CommandHandler {

    override fun canHandle(command: String): Boolean = command.startsWith("add player")

    override fun handle(command: String): String {
        val playerName = command.substring(11)

        if (players.exists(playerName)) {
            return printer.playerAlreadyExists(playerName)
        }

        players.add(playerName)

        return printer.players(players.names())
    }
}