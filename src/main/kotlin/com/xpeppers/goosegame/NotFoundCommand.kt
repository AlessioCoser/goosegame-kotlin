package com.xpeppers.goosegame

class NotFoundCommand(private val printer: Printer): GameCommand {
    override fun canRun(command: String): Boolean = true
    override fun run(command: String): String = printer.notFound()
}
