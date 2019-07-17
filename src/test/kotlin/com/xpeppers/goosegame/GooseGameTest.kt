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

    @Test
    fun `move player`() {
        game.execute("add player Pippo")
        game.execute("add player Pluto")

        val response = game.execute("move Pippo 4, 2")

        assertThat(response, `is`("Pippo rolls 4, 2. Pippo moves from Start to 6"))
    }

    @Test
    fun `move another player`() {
        game.execute("add player Pippo")
        game.execute("add player Pluto")

        val response = game.execute("move Pluto 5, 6")

        assertThat(response, `is`("Pluto rolls 5, 6. Pluto moves from Start to 11"))
    }

    @Test
    fun `move player from another position`() {
        game.execute("add player Pippo")
        game.execute("add player Pluto")

        game.execute("move Pluto 1, 1")
        val response = game.execute("move Pluto 2, 3")

        assertThat(response, `is`("Pluto rolls 2, 3. Pluto moves from 2 to 7"))
    }
}