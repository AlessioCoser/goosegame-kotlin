package com.xpeppers.goosegame

fun main() {
    while (true) {
        print(readLine()!!.toString() + "\n")
    }
}

class GooseGame {
    fun execute(command: String): String {
        return "players: " + command.substring(11)
    }
}
