package com.xpeppers.goosegame

class MovePlayer(private val players: Players, private val diceRoller: DiceRoller, private val printer: Printer) : Handler {
    override fun canHandle(command: String): Boolean = command.startsWith("move ")

    override fun handle(command: String): String {
        val elements = command.split(" ", ",")
        val player = players.find(elements[1])
        val dice = diceRoller.roll()

        players.updatePosition(player, player.position + dice.sum)

        return policies()
            .first { policy -> policy.canExecute(player) }
            .execute(player, dice)
    }

    private fun policies() = listOf(
        WinPolicy(printer),
        BouncePolicy(printer, players),
        BridgePolicy(printer, players),
        TheGoosePolicy(printer, players),
        PrankPolicy(printer, players),
        DefaultPolicy(printer)
    )
}