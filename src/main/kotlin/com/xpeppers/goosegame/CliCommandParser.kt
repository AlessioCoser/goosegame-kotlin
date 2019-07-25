package com.xpeppers.goosegame

class CliCommandParser : CommandParser {
    override fun parse(command: String) = when {
        command.startsWith("move ") -> Command.movePlayer(command.substring(5))
        command.startsWith("add player ") -> Command.addPlayer(command.substring(11))
        else -> Command.notFound()
    }
}
