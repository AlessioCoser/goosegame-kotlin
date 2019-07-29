package com.xpeppers.goosegame

import com.xpeppers.goosegame.policies.TheGoosePolicy
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TheGoosePolicyTest {
    @Test
    fun `can execute when not in spaces 5, 9, 14, 18, 23, 27`() {
        val policy = TheGoosePolicy(InMemoryPlayers())

        assertThat(policy.canExecute(playerIn(0)), `is`(false))
        assertThat(policy.canExecute(playerIn(15)), `is`(false))
        assertThat(policy.canExecute(playerIn(28)), `is`(false))
    }

    @Test
    fun `can execute when in spaces 5, 9, 14, 18, 23, 27`() {
        val policy = TheGoosePolicy(InMemoryPlayers())

        assertThat(policy.canExecute(playerIn(5)), `is`(true))
        assertThat(policy.canExecute(playerIn(9)), `is`(true))
        assertThat(policy.canExecute(playerIn(14)), `is`(true))
        assertThat(policy.canExecute(playerIn(18)), `is`(true))
        assertThat(policy.canExecute(playerIn(23)), `is`(true))
        assertThat(policy.canExecute(playerIn(27)), `is`(true))
    }

    private fun playerIn(positionValue: Int): Player {
        return Player("test").apply { position = positionValue }
    }
}