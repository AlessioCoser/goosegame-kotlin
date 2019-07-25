package com.xpeppers.goosegame

interface Parser {
    fun parse(command: String) : Command
}
