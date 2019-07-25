package com.xpeppers.goosegame

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CliParserTest {
    @Test
    fun `creates a not found command when command is not found`() {
        val parsed = CliParser("not found").parse()

        assertThat(parsed, `is`(Command.notFound()))
    }

    @Test
    fun `creates an add player command when command starts with add player`() {
        val parsed = CliParser("add player Pippo").parse()

        assertThat(parsed, `is`(Command.addPlayer("Pippo")))
    }

    @Test
    fun `creates a move player command when command start with move`() {
        val parsed = CliParser("move Pippo").parse()

        assertThat(parsed, `is`(Command.movePlayer("Pippo")))
    }
}