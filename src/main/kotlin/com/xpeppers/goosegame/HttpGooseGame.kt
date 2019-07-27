package com.xpeppers.goosegame

import spark.Spark.*

class HttpGooseGame(private val httpPort: Int) {
    fun start(): HttpGooseGame {
        val game = GooseGame(InMemoryPlayers(), RealDiceRoller(), HttpPrinter())

        port(httpPort)

        post("/players/add") { _, response ->
            response.status(200)
            response.type("application/json")

            game.run(Command.addPlayer("Pippo"))
        }

        awaitInitialization()
        return this
    }

    fun close() {
        stop()
        awaitStop()
    }
}
