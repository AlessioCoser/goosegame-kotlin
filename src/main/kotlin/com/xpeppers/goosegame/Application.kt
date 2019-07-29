package com.xpeppers.goosegame

fun main() {
    val players = InMemoryPlayers()
    val diceRoller = RealDiceRoller()

    listOf(
        HttpGooseGame(3000, players, diceRoller),
        CliGooseGame(players, diceRoller)
    ).forEach {
        it.start()
    }
}

