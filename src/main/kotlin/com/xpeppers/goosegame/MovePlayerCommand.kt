package com.xpeppers.goosegame

class MovePlayerCommand(val players: Players, val diceRoller: DiceRoller, val printer: Printer) : GameCommand {
    override fun canRun(command: String): Boolean = command.startsWith("move ")

    override fun run(command: String): String {
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