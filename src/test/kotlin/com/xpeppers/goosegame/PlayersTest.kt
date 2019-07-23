package com.xpeppers.goosegame

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class PlayersTest {
    private lateinit var players: Players

    @Before
    fun before() {
        players = Players()
        players.add("first")
        players.add("second")
    }

    @Test
    fun `empty list when no players are in that position except actual`() {
        val first = players.find("first")
        players.updatePosition(first, 5)

        val inSameSpace = players.inSamePositionOf(first)

        assertThat(inSameSpace, `is`(emptyList()))
    }

    @Test
    fun `list of players in that position except actual`() {
        val first = players.find("first")
        val second = players.find("second")

        val inSameSpace = players.inSamePositionOf(first)

        assertThat(inSameSpace, `is`(listOf(second)))
    }
}