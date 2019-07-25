package com.xpeppers.goosegame

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CliParserTest {
    @Test
    fun `creates a not found command when command is not found`() {
        val parser = CliParser()

        assertThat(parser.parse("not found"), `is`(Command.notFound()))
    }

    @Test
    fun `creates an add player command when command starts with add player`() {
        val parser = CliParser()

        assertThat(parser.parse("add player Pippo"), `is`(Command.addPlayer("Pippo")))
    }

    @Test
    fun `creates a move player command when command start with move`() {
        val parser = CliParser()

        assertThat(parser.parse("move Pippo"), `is`(Command.movePlayer("Pippo")))
    }
}