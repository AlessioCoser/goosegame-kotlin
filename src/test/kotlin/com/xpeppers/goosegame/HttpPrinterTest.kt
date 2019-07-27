package com.xpeppers.goosegame

import com.xpeppers.goosegame.GameResponse.Type.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class HttpPrinterTest {
    @Test
    fun `prints list of players`() {
        val response = HttpPrinter().players(listOf("Pippo", "pluto"))

        assertThat(response.type, `is`(OK))
        assertThat(response.message, `is`("{\"players\": [\"Pippo\", \"pluto\"]}"))
    }

    @Test
    fun `prints player already exists`() {
        val response = HttpPrinter().playerAlreadyExists("Pippo")

        assertThat(response.type, `is`(ALREADY_EXISTS))
        assertThat(response.message, `is`("{\"alreadyExists\": \"Pippo: Already existing player\"}"))
    }

    @Test
    fun `prints not found command`() {
        val response = HttpPrinter().notFound()

        assertThat(response.type, `is`(NOT_FOUND))
        assertThat(response.message, `is`(""))
    }

    @Test
    fun `prints move player from starting point`() {
        val response = HttpPrinter().movePlayer("Pippo", Dice(1, 2), 0, 3)

        assertThat(response.type, `is`(OK))
        assertThat(response.message, `is`("{\"Pippo\":{\"rolls\":[1,2],\"moves\":{\"from\":\"Start\",\"to\":\"3\"},\"status\":\"\"}}"))
    }

    @Test
    fun `prints move player to Win space`() {
        val player = playerAt("Pippo", 63)
        val response = HttpPrinter().win(player, Dice(60, 3), player.previousPosition)

        assertThat(response.type, `is`(OK))
        assertThat(response.message, `is`("{\"Pippo\":{\"rolls\":[60,3],\"moves\":{\"from\":\"Start\",\"to\":\"63\"},\"status\":\"Wins!\"}}"))
    }

    private fun playerAt(name: String, pos: Int): Player {
        return Player(name).apply { position = pos }
    }
}