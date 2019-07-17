package com.xpeppers.goosegame

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class GooseGameTest {
    @Test
    fun `add first player`() {
        val gooseGame = GooseGame()

        val response = gooseGame.execute("add player Pippo")

        assertThat(response, `is`("players: Pippo"))
    }

    @Test
    fun `add a players with different name`() {
        val game = GooseGame()

        val response = game.execute("add player Pluto")

        assertThat(response, `is`("players: Pluto"))
    }
}