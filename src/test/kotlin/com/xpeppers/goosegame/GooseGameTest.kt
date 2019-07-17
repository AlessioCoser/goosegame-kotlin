package com.xpeppers.goosegame

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class GooseGameTest {
    @Test
    fun `add first player`() {
        val response = GooseGame().execute("add player Pippo")

        assertThat(response, `is`("players: Pippo"))
    }
}