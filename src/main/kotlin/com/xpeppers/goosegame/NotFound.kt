package com.xpeppers.goosegame

class NotFound(private val printer: Printer): Handler {
    override fun canHandle(command: String): Boolean = true
    override fun handle(command: String): String = printer.notFound()
}
