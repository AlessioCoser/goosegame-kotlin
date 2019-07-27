package com.xpeppers.goosegame

class CliGooseGame(val players: Players, val diceRoller: RealDiceRoller, val printer: CliPrinter): GameRunner {
    private var running = true

    override fun start(): GameRunner {
        val game = GooseGame(players, diceRoller, printer)

        while (running) {
            val response = game.run(CliParser(readLine()!!.toString()).parse())
            print(response.message + "\n")
        }

        return this
    }

    override fun close() {
        running = false
    }
}