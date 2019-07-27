package com.xpeppers.goosegame

import com.eclipsesource.json.Json
import spark.Request

class HttpParser(private val request: Request) : Parser {
    override fun parse() = when {
        isAddPlayer() -> Command.addPlayer(addPlayerName())
        isMovePlayer() -> Command.movePlayer(movePlayerName())
        else -> Command.notFound()
    }

    private fun isAddPlayer() = request.pathInfo() == "/players/add" && request.requestMethod() == "POST"

    private fun addPlayerName() = Json.parse(request.body()).asObject().get("name").asString()

    private fun isMovePlayer() =
        request.pathInfo().startsWith("/players/") &&
                request.pathInfo().endsWith("/rolls") &&
                request.requestMethod() == "GET"

    private fun movePlayerName() =
        request.pathInfo().substring(9).replace("/rolls", "")
}
