package com.xpeppers.goosegame

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class GooseGameTest {
    private lateinit var game : GooseGame

    @Before
    fun before() {
        game = GooseGame(Players())
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
        addPlayers("Pluto")

        val response = game.execute("add player Pippo")

        assertThat(response, `is`("players: Pluto, Pippo"))
    }

    @Test
    fun `doesn't add a duplicated player`() {
        addPlayers("Pippo")

        val response = game.execute("add player Pippo")

        assertThat(response, `is`("Pippo: already existing player"))
    }

    @Test
    fun `move player`() {
        addPlayers("Pippo")

        val response = game.execute("move Pippo 4, 2")

        assertThat(response, `is`("Pippo rolls 4, 2. Pippo moves from Start to 6"))
    }

    @Test
    fun `move another player`() {
        addPlayers("Pippo", "Pluto")

        val response = game.execute("move Pluto 5, 6")

        assertThat(response, `is`("Pluto rolls 5, 6. Pluto moves from Start to 11"))
    }

    @Test
    fun `move player from another position`() {
        addPlayers("Pippo", "Pluto")

        game.execute("move Pluto 1, 1")
        val response = game.execute("move Pluto 2, 3")

        assertThat(response, `is`("Pluto rolls 2, 3. Pluto moves from 2 to 7"))
    }

    @Test
    fun `player wins`() {
        addPlayers("Pippo", "Pluto")

        val response = game.execute("move Pluto 60, 3")

        assertThat(response, `is`("Pluto rolls 60, 3. Pluto moves from Start to 63. Pluto Wins!!"))
    }

    @Test
    fun `player bounces when get over space 63`() {
        addPlayers("Pippo", "Pluto")

        val response = game.execute("move Pippo 60, 5")

        assertThat(response, `is`("Pippo rolls 60, 5. Pippo moves from Start to 63. Pippo bounces! Pippo returns to 61"))
    }

    @Test
    fun `game rolls dice autonomously`() {
        val diceRoller: DiceRoller = mock()
        whenever(diceRoller.roll()).thenReturn(Dice(1, 2))

        val game = GooseGame(Players(), diceRoller)
        game.execute("add player Pippo")
        game.execute("add player Pluto")

        val response = game.execute("move Pippo")

        assertThat(response, `is`("Pippo rolls 1, 2. Pippo moves from Start to 3"))
    }

    private fun addPlayers(vararg names: String): GooseGame {
        names.forEach { name -> game.execute("add player $name") }
        return game
    }
}