package com.xpeppers.goosegame

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class PlayerTest {
    @Test
    fun `player name`() {
        val player = Player("Pippo")

        assertThat(player.name, `is`("Pippo"))
    }

    @Test
    fun `player stating position is 0`() {
        val player = Player("Pippo")

        assertThat(player.position, `is`(0))
    }

    @Test
    fun `updatePosition moves player`() {
        val player = Player("Pippo")

        player.position = 12

        assertThat(player.position, `is`(12))
    }

    @Test
    fun `previousPosition tell the previous position of player`() {
        val player = Player("Pippo")

        player.position = 12
        player.position = 18

        assertThat(player.previousPosition, `is`(12))
    }
}