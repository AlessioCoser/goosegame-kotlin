package com.xpeppers.goosegame

class RealDiceRoller : DiceRoller {
    override fun roll(): Dice {
        return Dice(random(), random())
    }

    private fun random() = (1..6).random()
}
