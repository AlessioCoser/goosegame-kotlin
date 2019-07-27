package com.xpeppers.goosegame

import io.restassured.RestAssured.given
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test

class HttpGooseGameTest {
    private val port = 3000
    private lateinit var game : HttpGooseGame

    @Before
    fun setUp() {
        game = HttpGooseGame(port).start()
    }

    @After
    fun tearDown() {
        game.close()
    }

    @Test
    fun `add player route`() {
        given()
            .port(port)
            .contentType("application/json")
            .body("{\"name\":\"Pippo\"}")
            .post("/players/add")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .body("players", equalTo(listOf("Pippo")))
    }

    @Test
    fun `add an already existing player`() {
        given()
            .port(port)
            .contentType("application/json")
            .body("{\"name\":\"Pippo\"}")
            .post("/players/add")
            .then()
            .statusCode(200)

        given()
            .port(port)
            .contentType("application/json")
            .body("{\"name\":\"Pippo\"}")
            .post("/players/add")
            .then()
            .statusCode(409)
            .contentType("application/json")
            .body("error", equalTo("Pippo: Already existing player"))
    }
}