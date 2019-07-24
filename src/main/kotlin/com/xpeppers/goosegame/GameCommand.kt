package com.xpeppers.goosegame

interface GameCommand {
    fun canRun(command: String): Boolean
    fun run(command: String): String
}
