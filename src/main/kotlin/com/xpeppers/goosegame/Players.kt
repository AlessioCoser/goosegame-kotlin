package com.xpeppers.goosegame

class Players {
    private val players = mutableListOf<Player>()

    fun add(playerName: String) = players.add(Player(playerName))

    fun find(name: String) = players.first { player -> player.name == name }

    fun present(playerName: String) = names().contains(playerName)

    fun updatePosition(player: Player, newPosition: Int) {
        player.position = newPosition
    }

    fun names() = players.map(Player::name)

    fun inSamePositionOf(actual: Player): List<Player> {
        return players
            .filter { player -> player != actual && player.position == actual.position }
    }
}
