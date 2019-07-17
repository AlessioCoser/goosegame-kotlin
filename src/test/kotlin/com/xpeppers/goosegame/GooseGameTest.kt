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

    @Test
    fun `add two players`() {
        val game = GooseGame()

        game.execute("add player Pluto")
        val response = game.execute("add player Pippo")

        assertThat(response, `is`("players: Pluto, Pippo"))
    }

    @Test
    fun `doesn't add a duplicated player`() {
        val game = GooseGame()

        game.execute("add player Pippo")
        val response = game.execute("add player Pippo")

        assertThat(response, `is`("Pippo: already existing player"))
    }
}