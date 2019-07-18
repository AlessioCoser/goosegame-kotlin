package com.xpeppers.goosegame

class Player(private val name: String) {
    private var position = 0;

    fun name(): String {
        return name
    }

    fun position(): Int {
        return position;
    }

    fun updatePosition(newPosition: Int) {
        position = newPosition
    }
}
