package com.xpeppers.goosegame

class CliCommandParser : CommandParser {
    override fun parse(command: String): Command {
        if (command.startsWith("add player")) {
            return Command.addPlayer(command.substring(11))
        }
        return Command.notFound()
    }

}
