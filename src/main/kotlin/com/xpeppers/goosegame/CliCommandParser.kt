package com.xpeppers.goosegame

class CliCommandParser : CommandParser {
    override fun parse(command: String): Command {
        return Command.notFound()
    }

}
