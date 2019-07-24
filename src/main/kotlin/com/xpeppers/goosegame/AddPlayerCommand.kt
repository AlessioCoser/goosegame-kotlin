package com.xpeppers.goosegame

class AddPlayerCommand(private val printer: Printer, val players: Players): GameCommand {

    override fun canRun(command: String): Boolean = command.startsWith("add player")

    override fun run(command: String): String {
        val playerName = command.substring(11)


        if (players.present(playerName)) {
            return "$playerName: already existing player"
        }

        players.add(playerName)

        return "players: " + players.names().joinToString(", ")
    }
}