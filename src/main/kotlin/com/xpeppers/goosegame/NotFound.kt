package com.xpeppers.goosegame

class NotFound(private val printer: Printer): CommandHandler {
    override fun canHandle(command: Command): Boolean = true
    override fun handle(command: Command): GameResponse = printer.notFound()
}
