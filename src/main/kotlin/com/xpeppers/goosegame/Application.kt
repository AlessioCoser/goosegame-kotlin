package com.xpeppers.goosegame

fun main() {
    val players = InMemoryPlayers()
    val diceRoller = RealDiceRoller()

    listOf(
        HttpGooseGame(3000, players, diceRoller, HttpPrinter()),
        CliGooseGame(players, diceRoller, CliPrinter())
    ).forEach {
        it.start()
    }
}

