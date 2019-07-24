package com.xpeppers.goosegame

interface CommandHandler {
    fun canHandle(command: String): Boolean
    fun handle(command: String): String
}
