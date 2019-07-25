package com.xpeppers.goosegame

import spark.Spark.*

class HttpGooseGame(private val httpPort: Int) {
    fun start(): HttpGooseGame {

        port(httpPort)

        post("/players/add") { _, response ->
            response.status(200)
            response.type("application/json")

            "{\"players\": [\"Pippo\"]}"
        }

        awaitInitialization()
        return this
    }

    fun close() {
        stop()
        awaitStop()
    }
}
