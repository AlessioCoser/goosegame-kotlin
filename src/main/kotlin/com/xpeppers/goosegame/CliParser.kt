package com.xpeppers.goosegame

class CliParser(private val command: String) : Parser {

    override fun parse() = when {
        command.startsWith("move ") -> Command.movePlayer(command.substring(5))
        command.startsWith("add player ") -> Command.addPlayer(command.substring(11))
        else -> Command.notFound()
    }
}
