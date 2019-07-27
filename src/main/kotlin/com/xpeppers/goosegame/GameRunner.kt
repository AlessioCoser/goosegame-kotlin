package com.xpeppers.goosegame

interface GameRunner: AutoCloseable {
    fun start(): GameRunner
}
