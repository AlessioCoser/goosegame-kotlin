package com.xpeppers.goosegame

class InMemoryPlayers: Players {
    private val players = mutableListOf<Player>()

    override fun add(playerName: String) {
        players.add(Player(playerName))
    }

    override fun find(name: String) = players.first { player -> player.name == name }

    override fun exists(playerName: String) = names().contains(playerName)

    override fun updatePosition(player: Player, newPosition: Int) {
        player.position = newPosition
    }

    override fun names() = players.map(Player::name)

    override fun inSamePositionOf(actual: Player): List<Player> {
        return players
            .filter { player -> player != actual && player.position == actual.position }
    }
}

