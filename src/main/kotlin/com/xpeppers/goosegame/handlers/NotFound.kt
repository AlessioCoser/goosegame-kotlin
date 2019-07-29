package com.xpeppers.goosegame.handlers

import com.xpeppers.goosegame.Command
import com.xpeppers.goosegame.GameResponse.*

class NotFound : CommandHandler {
    override fun canHandle(command: Command): Boolean = true
    override fun handle(command: Command) = NotFoundResponse("")
}
