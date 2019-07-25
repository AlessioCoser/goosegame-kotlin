package com.xpeppers.goosegame

interface CommandHandler {
    fun canHandle(command: Command): Boolean
    fun handle(command: Command): String
}
