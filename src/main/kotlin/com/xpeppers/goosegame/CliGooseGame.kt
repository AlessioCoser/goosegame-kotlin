package com.xpeppers.goosegame

class CliGooseGame(private val players: Players, private val diceRoller: RealDiceRoller): GameRunner {
    private var running = true

    override fun start(): GameRunner {
        val game = GooseGame(players, diceRoller)

        while (running) {
            val response = game.run(CliParser(readLine()!!.toString()).parse())
            print(CliPrinter().print(response))
        }

        return this
    }

    override fun close() {
        running = false
    }
}