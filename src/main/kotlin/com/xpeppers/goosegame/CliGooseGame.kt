package com.xpeppers.goosegame

class CliGooseGame(val players: Players, val diceRoller: RealDiceRoller): GameRunner {
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