package com.xpeppers.goosegame

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class HttpPrinterTest {
    @Test
    fun `prints list of players`() {
        val response = HttpPrinter().print(GameResponse.PlayersResponse(listOf("Pippo", "pluto")))

        assertThat(response, `is`("{\"players\": [\"Pippo\", \"pluto\"]}"))
    }

    @Test
    fun `prints player already exists`() {
        val response = HttpPrinter().print(GameResponse.AlreadyExistsResponse("Pippo"))

        assertThat(response, `is`("{\"alreadyExists\": \"Pippo: Already existing player\"}"))
    }

    @Test
    fun `prints not found command`() {
        val response = HttpPrinter().print(GameResponse.NotFoundResponse(""))

        assertThat(response, `is`(""))
    }

    @Test
    fun `prints move player from starting point`() {
        val response = HttpPrinter().print(GameResponse.NormalResponse("Pippo", 0, 3, Dice(1, 2)))

        assertThat(response, `is`("{\"Pippo\":{\"rolls\":[1,2],\"moves\":{\"from\":\"Start\",\"to\":\"3\"},\"status\":\"\"}}"))
    }

    @Test
    fun `prints move player to Win space`() {
        val player = playerAt("Pippo", 63)
        val response = HttpPrinter().print(GameResponse.WinResponse(player.name, player.previousPosition, player.position, Dice(60, 3)))

        assertThat(response, `is`("{\"Pippo\":{\"rolls\":[60,3],\"moves\":{\"from\":\"Start\",\"to\":\"63\"},\"status\":\"Wins!\"}}"))
    }

    @Test
    fun `prints move player over Win space and Bounce`() {
        val player = playerAt("Pippo", 61)
        val response = HttpPrinter().print(GameResponse.BounceResponse(player.name, player.previousPosition, player.position, Dice(60, 5), 63))

        assertThat(response, `is`("{\"Pippo\":{\"rolls\":[60,5],\"moves\":{\"from\":\"Start\",\"to\":\"61\"},\"status\":\"Bounces!\"}}"))
    }

    @Test
    fun `prints move player to the Bridge`() {
        val player = playerAt("Pippo", 12)
        val response = HttpPrinter().print(GameResponse.BridgeResponse(player.name, player.previousPosition, 6, Dice(4, 2)))

        assertThat(response, `is`("{\"Pippo\":{\"rolls\":[4,2],\"moves\":{\"from\":\"Start\",\"to\":\"12\"},\"status\":\"Jumps!\"}}"))
    }

    @Test
    fun `prints move player to the Goose`() {
        val player = playerAt("Pippo", 10)
        val response = HttpPrinter().print(GameResponse.GooseResponse(player.name, 0, 5, Dice(3, 2), mutableListOf(10)))

        assertThat(response, `is`("{\"Pippo\":{\"rolls\":[3,2],\"moves\":{\"from\":\"Start\",\"to\":\"10\"},\"status\":\"Moves again!\"}}"))
    }

    @Test
    fun `prints move player to another players's space`() {
        val other = playerAt("Pluto", 2, 5)
        val player = playerAt("Pippo", 5, 2)
        val response = HttpPrinter().print(GameResponse.PrankResponse(player.name, 2, 5, Dice(1, 2), other))

        assertThat(response, `is`("{\"Pippo\":{\"rolls\":[1,2],\"moves\":{\"from\":\"2\",\"to\":\"5\"},\"status\":\"Pluto returns to 2!\"}}"))
    }

    private fun playerAt(name: String, current: Int, previous: Int = 0): Player {
        return Player(name).apply {
            position = current
            previousPosition = previous
        }
    }
}