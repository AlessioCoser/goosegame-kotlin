package com.xpeppers.goosegame

class HttpPrinter() : Printer {
    override fun prank(other: Player): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun theGoose(name: String, beginPosition: Int, landPosition: Int, dice: Dice, moves: MutableList<Int>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bridge(player: Player, dice: Dice, previousPosition: Int, bridgeSpace: Int): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun win(player: Player, dice: Dice, previousPosition: Int): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bounce(player: Player, dice: Dice, previousPosition: Int, winSpace: Int): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun movePlayer(name: String, dice: Dice, previousPosition: Int, newPosition: Int): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun playerAlreadyExists(playerName: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun players(names: List<String>): String {
        return "{\"players\": [\"${names.joinToString("\", \"")}\"]}"
    }

    override fun notFound(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

