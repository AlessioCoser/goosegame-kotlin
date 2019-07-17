package com.xpeppers.goosegame

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class GooseGameTest {
    private lateinit var game: GooseGame

    @Before
    fun beforeEach() {
        game = GooseGame()
    }

    @Test
    fun `add first player`() {
        val response = game.execute("add player Pippo")

        assertThat(response, `is`("players: Pippo"))
    }

    @Test
    fun `add a players with different name`() {
        val response = game.execute("add player Pluto")

        assertThat(response, `is`("players: Pluto"))
    }

    @Test
    fun `add two players`() {
        game.execute("add player Pluto")
        val response = game.execute("add player Pippo")

        assertThat(response, `is`("players: Pluto, Pippo"))
    }

    @Test
    fun `doesn't add a duplicated player`() {
        game.execute("add player Pippo")
        val response = game.execute("add player Pippo")

        assertThat(response, `is`("Pippo: already existing player"))
    }
}