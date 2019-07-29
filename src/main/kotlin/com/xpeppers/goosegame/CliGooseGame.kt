package com.xpeppers.goosegame

class CliGooseGame(val players: Players, val diceRoller: RealDiceRoller): GameRunner {
    private var running = true

    override fun start(): GameRunner {
        val printer = CliPrinter()
        val game = GooseGame(players, diceRoller)

        while (running) {
            val response = game.run(CliParser(readLine()!!.toString()).parse())
            print(printer.print(response))
        }

        return this
    }

    override fun close() {
        running = false
    }
}