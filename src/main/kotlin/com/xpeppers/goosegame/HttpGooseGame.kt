package com.xpeppers.goosegame

import com.eclipsesource.json.Json
import spark.Request
import spark.Response
import spark.Spark.*

class HttpGooseGame(private val httpPort: Int) {
    fun start(): HttpGooseGame {
        val game = GooseGame(InMemoryPlayers(), RealDiceRoller(), HttpPrinter())

        port(httpPort)
        post("/players/add", addPlayerRoute(game))

        awaitInitialization()
        return this
    }

    fun close() {
        stop()
        awaitStop()
    }

    private fun addPlayerRoute(game: GooseGame): (request: Request, response: Response) -> String {
        return { request, response ->
            response.type("application/json")

            val result = game.run(Command.addPlayer(playerNameFrom(request)))

            response.status(200)
            if (result.type == GameResponse.Type.ERROR) {
                response.status(409)
            }

            result.message
        }
    }

    private fun playerNameFrom(request: Request) = Json.parse(request.body()).asObject().get("name").asString()
}
