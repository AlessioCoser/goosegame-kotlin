package com.xpeppers.goosegame

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import spark.Request

class HttpParserTest {
    @Test
    fun `parse add player http command`() {
        val request: Request = mock()
        whenever(request.requestMethod()).thenReturn("POST")
        whenever(request.pathInfo()).thenReturn("/players/add")
        whenever(request.body()).thenReturn("{\"name\": \"Pippo\"}")

        assertThat(HttpParser(request).parse(), `is`(Command.addPlayer("Pippo")))
    }

    @Test
    fun `parse move player http command`() {
        val request: Request = mock()
        whenever(request.requestMethod()).thenReturn("GET")
        whenever(request.pathInfo()).thenReturn("/players/Pippo/rolls")

        assertThat(HttpParser(request).parse(), `is`(Command.movePlayer("Pippo")))
    }
}