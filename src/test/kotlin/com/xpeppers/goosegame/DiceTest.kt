package com.xpeppers.goosegame

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class DiceTest {
    @Test
    fun `sums dice`() {
        assertThat(Dice(1, 2).sum, `is`(3))
    }
}