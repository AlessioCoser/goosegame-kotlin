package com.xpeppers.goosegame

interface CommandParser {
    fun parse(command: String) : Command
}
