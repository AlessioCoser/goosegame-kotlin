package com.xpeppers.goosegame

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class GooseGameTest {
    private lateinit var game: GooseGame
    private lateinit var diceRoller: DiceRoller

    @Before
    fun before() {
        diceRoller = mock()
        game = GooseGame(InMemoryPlayers(), diceRoller, CliPrinter())
    }

    @Test
    fun `add first player`() {
        val response = game.run(Command.addPlayer("Pippo"))

        assertThat(response.message, `is`("players: Pippo"))
    }

    @Test
    fun `add a players with different name`() {
        val response = game.run(Command.addPlayer("Pluto"))

        assertThat(response.message, `is`("players: Pluto"))
    }

    @Test
    fun `add two players`() {
        addPlayers("Pluto")

        val response = game.run(Command.addPlayer("Pippo"))

        assertThat(response.message, `is`("players: Pluto, Pippo"))
    }

    @Test
    fun `doesn't add a duplicated player`() {
        addPlayers("Pippo")

        val response = game.run(Command.addPlayer("Pippo"))

        assertThat(response.message, `is`("Pippo: already existing player"))
    }

    @Test
    fun `move player`() {
        addPlayers("Pippo")

        val response = moveCommand("Pippo", Dice(4, 3))

        assertThat(response.message, `is`("Pippo rolls 4, 3. Pippo moves from Start to 7"))
    }

    @Test
    fun `move another player`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pluto", Dice(5, 6))

        assertThat(response.message, `is`("Pluto rolls 5, 6. Pluto moves from Start to 11"))
    }

    @Test
    fun `move player from another position`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pluto", Dice(1, 1), Dice(2, 3))

        assertThat(response.message, `is`("Pluto rolls 2, 3. Pluto moves from 2 to 7"))
    }

    @Test
    fun `player wins`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pluto", Dice(60, 3))

        assertThat(response.message, `is`("Pluto rolls 60, 3. Pluto moves from Start to 63. Pluto Wins!!"))
    }

    @Test
    fun `player bounces when get over space 63`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pippo", Dice(60, 5))

        assertThat(response.message, `is`("Pippo rolls 60, 5. Pippo moves from Start to 63. Pippo bounces! Pippo returns to 61"))
    }

    @Test
    fun `game rolls dice autonomously`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pippo", Dice(1, 2))

        assertThat(response.message, `is`("Pippo rolls 1, 2. Pippo moves from Start to 3"))
    }

    @Test
    fun `Space "6" is "The Bridge"`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pippo", Dice(2, 4))

        assertThat(response.message, `is`("Pippo rolls 2, 4. Pippo moves from Start to The Bridge. Pippo jumps to 12"))
    }

    @Test
    fun `player in space "The Bridge" moves to space 12`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pippo", Dice(2, 4), Dice(1, 2))

        assertThat(response.message, `is`("Pippo rolls 1, 2. Pippo moves from 12 to 15"))
    }

    @Test
    fun `player on space 5 is on "The Goose"`() {
        addPlayers("Pippo")

        val response = moveCommand("Pippo", Dice(2, 3))

        assertThat(response.message, `is`("Pippo rolls 2, 3. Pippo moves from Start to 5, The Goose. Pippo moves again and goes to 10"))
    }

    @Test
    fun `player on space The Goose moves again`() {
        addPlayers("Pippo")

        val response = moveCommand("Pippo", Dice(2, 3), Dice(1, 1))

        assertThat(response.message, `is`("Pippo rolls 1, 1. Pippo moves from 10 to 12"))
    }

    @Test
    fun `player on space The Goose can jump multiple times`() {
        addPlayers("Pippo")

        val response = moveCommand("Pippo", Dice(5, 5), Dice(2, 2))

        assertThat(response.message, `is`("Pippo rolls 2, 2. Pippo moves from 10 to 14, The Goose. Pippo moves again and goes to 18, The Goose. Pippo moves again and goes to 22"))
    }

    @Test
    fun `player on space occupied by another player, sends him to player's previous position`() {
        addPlayers("Pippo", "Pluto")

        moveCommand("Pluto", Dice(5, 3))
        val response = moveCommand("Pippo", Dice(4, 4))

        assertThat(response.message, `is`("Pippo rolls 4, 4. Pippo moves from Start to 8. On 8 there is Pluto, who returns to Start"))
    }

    @Test
    fun `when command not found print error`() {
        assertThat(game.run(Command.notFound()).message, `is`("Error: command not found"))
    }

    private fun addPlayers(vararg names: String): GooseGame {
        names.forEach { name -> game.run(Command.addPlayer(name)) }
        return game
    }

    private fun moveCommand(name: String, vararg dice: Dice): GameResponse {
        whenever(diceRoller.roll()).thenReturn(dice.first(), *dice.drop(1).toTypedArray())

        return dice.map { game.run(Command.movePlayer(name)) }.last()
    }
}
