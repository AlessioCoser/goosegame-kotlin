package com.xpeppers.goosegame

class WinPolicy : Policy {
    private val winSpace = 63

    override fun canExecute(player: Player): Boolean {
        return player.position == winSpace
    }

    override fun execute(player: Player, dice: Dice): GameResponse {
        return GameResponse.WinResponse(player.name, player.previousPosition, player.position, dice)
    }
}