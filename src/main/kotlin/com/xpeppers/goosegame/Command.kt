package com.xpeppers.goosegame

data class Command(val name: String, val type: Type) {
    enum class Type {
        NOT_FOUND,
        ADD_PLAYER,
        MOVE_PLAYER
    }

    companion object {
        fun notFound() = Command("", Type.NOT_FOUND)
        fun addPlayer(name: String) = Command(name, Type.ADD_PLAYER)
        fun movePlayer(name: String) = Command(name, Type.MOVE_PLAYER)
    }
}
