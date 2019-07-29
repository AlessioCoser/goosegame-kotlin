package com.xpeppers.goosegame

class NotFound : CommandHandler {
    override fun canHandle(command: Command): Boolean = true
    override fun handle(command: Command) = GameResponse.NotFoundResponse("")
}
