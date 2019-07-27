package com.xpeppers.goosegame

import com.xpeppers.goosegame.GameResponse.Type.ALREADY_EXISTS
import com.xpeppers.goosegame.GameResponse.Type.OK
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
}