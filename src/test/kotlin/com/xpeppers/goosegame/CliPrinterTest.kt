package com.xpeppers.goosegame

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CliPrinterTest {
    @Test
    fun `prints list of players`() {
        val response = CliPrinter().print(GameResponse.PlayersResponse(listOf("Pippo", "Pluto")))

        assertThat(response, `is`("players: Pippo, Pluto\n"))
    }

    @Test
    fun `prints player already exists`() {
        val response = CliPrinter().print(GameResponse.AlreadyExistsResponse("Pippo"))

        assertThat(response, `is`("Pippo: already existing player\n"))
    }

    @Test
    fun `prints not found command`() {
        val response = CliPrinter().print(GameResponse.NotFoundResponse(""))

        assertThat(response, `is`("Error: command not found\n"))
    }

    @Test
    fun `prints move player from starting point`() {
        val response = CliPrinter().print(GameResponse.NormalResponse("Pippo", 0, 3, Dice(1, 2)))

        assertThat(response, `is`("Pippo rolls 1, 2. Pippo moves from Start to 3\n"))
    }

    @Test
    fun `prints move player to Win space`() {
        val player = playerAt("Pippo", 63)
        val response = CliPrinter().print(GameResponse.WinResponse(player.name, player.previousPosition, player.position, Dice(60, 3)))

        assertThat(response, `is`("Pippo rolls 60, 3. Pippo moves from Start to 63. Pippo Wins!!\n"))
    }

    @Test
    fun `prints move player over Win space and Bounce`() {
        val player = playerAt("Pippo", 61)
        val response = CliPrinter().print(GameResponse.BounceResponse(player.name, player.previousPosition, player.position, Dice(60, 5), 63))

        assertThat(response, `is`("Pippo rolls 60, 5. Pippo moves from Start to 63. Pippo bounces! Pippo returns to 61\n"))
    }

    @Test
    fun `prints move player to the Bridge`() {
        val player = playerAt("Pippo", 12)
        val response = CliPrinter().print(GameResponse.BridgeResponse(player.name, player.previousPosition, 6, Dice(4, 2)))

        assertThat(response, `is`("Pippo rolls 4, 2. Pippo moves from Start to The Bridge. Pippo jumps to 12\n"))
    }

    @Test
    fun `prints move player to the Goose`() {
        val player = playerAt("Pippo", 10)
        val response = CliPrinter().print(GameResponse.GooseResponse(player.name, 0, 5, Dice(3, 2), mutableListOf(10)))

        assertThat(response, `is`("Pippo rolls 3, 2. Pippo moves from Start to 5, The Goose. Pippo moves again and goes to 10\n"))
    }

    @Test
    fun `prints move player to another players's space`() {
        val other = playerAt("Pluto", 2, 5)
        val player = playerAt("Pippo", 5, 2)
        val response = CliPrinter().print(GameResponse.PrankResponse(player.name, 2, 5, Dice(1, 2), other))

        assertThat(response, `is`("Pippo rolls 1, 2. Pippo moves from 2 to 5. On 5 there is Pluto, who returns to 2\n"))
    }

    private fun playerAt(name: String, current: Int, previous: Int = 0): Player {
        return Player(name).apply {
            position = current
            previousPosition = previous
        }
    }
}