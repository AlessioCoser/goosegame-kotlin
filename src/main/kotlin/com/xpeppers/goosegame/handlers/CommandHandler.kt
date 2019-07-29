package com.xpeppers.goosegame.handlers

import com.xpeppers.goosegame.Command
import com.xpeppers.goosegame.GameResponse

interface CommandHandler {
    fun canHandle(command: Command): Boolean
    fun handle(command: Command): GameResponse
}
