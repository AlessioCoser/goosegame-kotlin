package com.xpeppers.goosegame

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class GooseGameTest {
    @Test
    fun `add first player`() {
        val response = GooseGame().execute("add player Pippo")

        assertThat(response, `is`("players: Pippo"))
    }

    @Test
    fun `add a players with different name`() {
        val response = GooseGame().execute("add player Pluto")

        assertThat(response, `is`("players: Pluto"))
    }

    @Test
    fun `add two players`() {
        val game = gameWith("Pluto")

        val response = game.execute("add player Pippo")

        assertThat(response, `is`("players: Pluto, Pippo"))
    }

    @Test
    fun `doesn't add a duplicated player`() {
        val game = gameWith("Pippo")

        val response = game.execute("add player Pippo")

        assertThat(response, `is`("Pippo: already existing player"))
    }

    @Test
    fun `move player`() {
        val game = gameWith("Pippo")

        val response = game.execute("move Pippo 4, 2")

        assertThat(response, `is`("Pippo rolls 4, 2. Pippo moves from Start to 6"))
    }

    @Test
    fun `move another player`() {
        val game = gameWith("Pippo", "Pluto")

        val response = game.execute("move Pluto 5, 6")

        assertThat(response, `is`("Pluto rolls 5, 6. Pluto moves from Start to 11"))
    }

    @Test
    fun `move player from another position`() {
        val game = gameWith("Pippo", "Pluto")

        game.execute("move Pluto 1, 1")
        val response = game.execute("move Pluto 2, 3")

        assertThat(response, `is`("Pluto rolls 2, 3. Pluto moves from 2 to 7"))
    }

    @Test
    fun `player wins`() {
        val game = gameWith("Pippo", "Pluto")

        val response = game.execute("move Pluto 60, 3")

        assertThat(response, `is`("Pluto rolls 60, 3. Pluto moves from Start to 63. Pluto Wins!!"))
    }

    private fun gameWith(vararg names: String): GooseGame {
        val game = GooseGame()
        names.forEach { name -> game.execute("add player $name") }
        return game
    }
}