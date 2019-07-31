package com.xpeppers.goosegame

fun main() {
    val game = GooseGame(InMemoryPlayers(), RealDiceRoller())

    listOf(
        HttpGooseGame(3000, game),
        CliGooseGame(game)
    ).forEach {
        it.start()
    }
}

