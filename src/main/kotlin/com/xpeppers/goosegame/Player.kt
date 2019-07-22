package com.xpeppers.goosegame

data class Player(val name: String) {
    var previousPosition: Int = 0
    var position: Int = 0
        set(newPosition) {
            this.previousPosition = position
            field = newPosition
        }
}
