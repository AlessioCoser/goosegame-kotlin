package com.xpeppers.goosegame

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.xpeppers.goosegame.GameResponse.*
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
        game = GooseGame(InMemoryPlayers(), diceRoller)
    }

    @Test
    fun `add first player`() {
        val response = game.run(Command.addPlayer("Pippo"))

        assertThat(response as PlayersResponse, `is`(PlayersResponse(listOf("Pippo"))))
    }

    @Test
    fun `add a players with different name`() {
        val response = game.run(Command.addPlayer("Pluto"))

        assertThat(response as PlayersResponse, `is`(PlayersResponse(listOf("Pluto"))))
    }

    @Test
    fun `add two players`() {
        addPlayers("Pluto")

        val response = game.run(Command.addPlayer("Pippo"))

        assertThat(response as PlayersResponse, `is`(PlayersResponse(listOf("Pluto", "Pippo"))))
    }

    @Test
    fun `doesn't add a duplicated player`() {
        addPlayers("Pippo")

        val response = game.run(Command.addPlayer("Pippo"))

        assertThat(response as AlreadyExistsResponse, `is`(AlreadyExistsResponse("Pippo")))
    }

    @Test
    fun `move player`() {
        addPlayers("Pippo")

        val response = moveCommand("Pippo", Dice(4, 3))

        assertThat(response as NormalResponse, `is`(NormalResponse("Pippo", 0, 7, Dice(4, 3))))
    }

    @Test
    fun `move another player`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pluto", Dice(5, 6))

        assertThat(response as NormalResponse, `is`(NormalResponse("Pluto", 0, 11, Dice(5, 6))))
    }

    @Test
    fun `move player from another position`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pluto", Dice(1, 1), Dice(2, 3))

        assertThat(response as NormalResponse, `is`(NormalResponse("Pluto", 2, 7, Dice(2, 3))))
    }

    @Test
    fun `player wins`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pluto", Dice(60, 3))

        assertThat(response as WinResponse, `is`(WinResponse("Pluto", 0, 63, Dice(60, 3))))
    }

    @Test
    fun `player bounces when get over space 63`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pippo", Dice(60, 5))

        assertThat(response as BounceResponse, `is`(BounceResponse("Pippo", 0, 61, Dice(60, 5), 63)))
    }

    @Test
    fun `Space "6" is "The Bridge"`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pippo", Dice(2, 4))

        assertThat(response as BridgeResponse, `is`(BridgeResponse("Pippo", 0, 6, Dice(2, 4))))
    }

    @Test
    fun `player in space "The Bridge" moves to space 12`() {
        addPlayers("Pippo", "Pluto")

        val response = moveCommand("Pippo", Dice(2, 4), Dice(1, 2))

        assertThat(response as NormalResponse, `is`(NormalResponse("Pippo", 12, 15, Dice(1, 2))))
    }

    @Test
    fun `player on space 5 is on "The Goose"`() {
        addPlayers("Pippo")

        val response = moveCommand("Pippo", Dice(2, 3))

        assertThat(response as GooseResponse, `is`(GooseResponse("Pippo", 0, 5, Dice(2, 3), mutableListOf(10))))
    }

    @Test
    fun `player on space The Goose moves again`() {
        addPlayers("Pippo")

        val response = moveCommand("Pippo", Dice(2, 3), Dice(1, 1))

        assertThat(response as NormalResponse, `is`(NormalResponse("Pippo", 10, 12, Dice(1, 1))))
    }

    @Test
    fun `player on space The Goose can jump multiple times`() {
        addPlayers("Pippo")

        val response = moveCommand("Pippo", Dice(5, 5), Dice(2, 2))

        assertThat(response as GooseResponse, `is`(GooseResponse("Pippo", 10, 14, Dice(2, 2), mutableListOf(18, 22))))
    }

    @Test
    fun `player on space occupied by another player, sends him to player's previous position`() {
        addPlayers("Pippo", "Pluto")

        moveCommand("Pluto", Dice(5, 3))
        val response = moveCommand("Pippo", Dice(4, 4))


        assertThat(response as PrankResponse, `is`(PrankResponse("Pippo", 0, 8, Dice(4, 4), Player("Pluto").apply { position = 0; previousPosition = 8 })))
    }

    @Test
    fun `when command not found print error`() {
        assertThat(game.run(Command.notFound()) as NotFoundResponse, `is`(NotFoundResponse("")))
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
