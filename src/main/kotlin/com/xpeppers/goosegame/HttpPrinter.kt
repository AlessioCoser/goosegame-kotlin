package com.xpeppers.goosegame

class HttpPrinter() : Printer {
    override fun prank(name: String, beginPosition: Int, landPosition: Int, dice: Dice, other: Player): GameResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun theGoose(name: String, beginPosition: Int, landPosition: Int, dice: Dice, moves: MutableList<Int>): GameResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bridge(player: Player, dice: Dice, previousPosition: Int, bridgeSpace: Int): GameResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun win(player: Player, dice: Dice, previousPosition: Int): GameResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bounce(player: Player, dice: Dice, previousPosition: Int, winSpace: Int): GameResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun movePlayer(name: String, dice: Dice, previousPosition: Int, newPosition: Int): GameResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playerAlreadyExists(playerName: String): GameResponse {
        return GameResponse.ok("{\"error\": \"$playerName: Already existing player\"}")
    }

    override fun players(names: List<String>): GameResponse {
        return GameResponse.ok("{\"players\": [\"${names.joinToString("\", \"")}\"]}")
    }

    override fun notFound(): GameResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

