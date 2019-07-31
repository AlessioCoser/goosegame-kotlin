package com.xpeppers.goosegame

class CliGooseGame(private val game: GooseGame): GameRunner {
    private var running = true

    override fun start(): GameRunner {
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