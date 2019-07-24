package com.xpeppers.goosegame

interface Handler {
    fun canHandle(command: String): Boolean
    fun handle(command: String): String
}
