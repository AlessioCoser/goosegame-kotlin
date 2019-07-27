package com.xpeppers.goosegame

interface Policy {
    fun canExecute(player: Player): Boolean
    fun execute(player: Player, dice: Dice): GameResponse
}
