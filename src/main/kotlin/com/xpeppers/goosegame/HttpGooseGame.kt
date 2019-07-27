package com.xpeppers.goosegame

import spark.Spark.*

class HttpGooseGame(private val httpPort: Int, val diceRoller: DiceRoller) {
    fun start(): HttpGooseGame {
        val game = GooseGame(InMemoryPlayers(), diceRoller, HttpPrinter())

        port(httpPort)

        before("*") { request, response ->
            response.type("application/json")

            val gameResponse = game.run(HttpParser(request).parse())
            halt(statusFor(gameResponse), gameResponse.message)
        }

        awaitInitialization()
        return this
    }

    fun close() {
        stop()
        awaitStop()
    }

    private fun statusFor(gameResponse: GameResponse) = when (gameResponse.type) {
        GameResponse.Type.OK -> 200
        GameResponse.Type.ALREADY_EXISTS -> 409
        GameResponse.Type.NOT_FOUND -> 404
    }
}
