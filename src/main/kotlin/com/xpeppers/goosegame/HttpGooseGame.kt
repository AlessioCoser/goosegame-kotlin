package com.xpeppers.goosegame

import spark.Spark.*

class HttpGooseGame(private val httpPort: Int, private val players: InMemoryPlayers, private val diceRoller: DiceRoller, private val printer: Printer): GameRunner {
    override fun start(): GameRunner {
        val game = GooseGame(players, diceRoller, printer)

        port(httpPort)

        before("*") { request, response ->
            response.type("application/json")

            val gameResponse = game.run(HttpParser(request).parse())
            halt(statusFor(gameResponse), gameResponse.message)
        }

        awaitInitialization()
        return this
    }

    override fun close() {
        stop()
        awaitStop()
    }

    private fun statusFor(gameResponse: GameResponse) = when (gameResponse.type) {
        GameResponse.Type.OK -> 200
        GameResponse.Type.ALREADY_EXISTS -> 409
        GameResponse.Type.NOT_FOUND -> 404
    }
}
