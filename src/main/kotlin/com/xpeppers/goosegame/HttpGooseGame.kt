package com.xpeppers.goosegame

import com.xpeppers.goosegame.GameResponse.*
import spark.Spark.*

class HttpGooseGame(private val httpPort: Int, private val game: GooseGame): GameRunner {
    override fun start(): GameRunner {
        port(httpPort)

        before("*") { request, response ->
            response.type("application/json")

            val gameResponse = game.run(HttpParser(request).parse())
            halt(statusFor(gameResponse), HttpPrinter().print(gameResponse))
        }

        awaitInitialization()
        return this
    }

    override fun close() {
        stop()
        awaitStop()
    }

    private fun statusFor(gameResponse: GameResponse) = when (gameResponse) {
        is PrankResponse -> 200
        is GooseResponse -> 200
        is BridgeResponse -> 200
        is WinResponse -> 200
        is BounceResponse -> 200
        is NormalResponse -> 200
        is AlreadyExistsResponse -> 409
        is PlayersResponse -> 200
        is NotFoundResponse -> 404
    }
}
