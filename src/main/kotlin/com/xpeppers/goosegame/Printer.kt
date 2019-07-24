package com.xpeppers.goosegame

interface Printer {
    fun prank(other: Player): String

    fun theGoose(name: String, beginPosition: Int, landPosition: Int, dice: Dice, moves: MutableList<Int>): String

    fun bridge(player: Player, dice: Dice, previousPosition: Int, bridgeSpace: Int): String

    fun win(player: Player, dice: Dice, previousPosition: Int): String

    fun bounce(player: Player, dice: Dice, previousPosition: Int, winSpace: Int): String

    fun movePlayer(name: String, dice: Dice, previousPosition: Int, newPosition: Int): String

    fun playerAlreadyExists(playerName: String): String

    fun players(names: List<String>): String

    fun notFound(): String
}