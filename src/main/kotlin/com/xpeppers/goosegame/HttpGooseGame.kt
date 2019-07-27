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
            response.status(200)
            response.type("application/json")

            game.run(Command.addPlayer(playerNameFrom(request)))
        }
    }

    private fun playerNameFrom(request: Request) = Json.parse(request.body()).asObject().get("name").asString()
}
