package com.xpeppers.goosegame

interface Players {
    fun add(playerName: String)

    fun find(name: String) : Player

    fun exists(playerName: String) : Boolean

    fun updatePosition(player: Player, newPosition: Int)

    fun names() : List<String>

    fun inSamePositionOf(actual: Player): List<Player>
}
