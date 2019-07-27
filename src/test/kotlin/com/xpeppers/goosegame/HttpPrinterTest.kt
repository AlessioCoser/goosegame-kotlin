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
}