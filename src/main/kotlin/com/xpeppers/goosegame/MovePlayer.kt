package com.xpeppers.goosegame

class MovePlayer(private val players: Players, private val diceRoller: DiceRoller, private val printer: Printer) : CommandHandler {
    override fun canHandle(command: Command): Boolean =
        command.type == Command.Type.MOVE_PLAYER

    override fun handle(command: Command): String {
        val player = players.find(command.name)
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