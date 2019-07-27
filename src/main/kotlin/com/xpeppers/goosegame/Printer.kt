package com.xpeppers.goosegame

interface Printer {
    fun prank(name: String, beginPosition: Int, landPosition: Int, dice: Dice, other: Player): GameResponse

    fun theGoose(name: String, beginPosition: Int, landPosition: Int, dice: Dice, moves: MutableList<Int>): GameResponse

    fun bridge(player: Player, dice: Dice, previousPosition: Int, bridgeSpace: Int): GameResponse

    fun win(player: Player, dice: Dice, previousPosition: Int): GameResponse

    fun bounce(player: Player, dice: Dice, previousPosition: Int, winSpace: Int): GameResponse

    fun movePlayer(name: String, dice: Dice, previousPosition: Int, newPosition: Int): GameResponse

    fun playerAlreadyExists(playerName: String): GameResponse

    fun players(names: List<String>): GameResponse

    fun notFound(): GameResponse
}