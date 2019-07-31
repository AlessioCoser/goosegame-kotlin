package com.xpeppers.goosegame

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test

class HttpGooseGameTest {
    private val port = 3000
    private lateinit var game : GameRunner
    private lateinit var diceRoller: DiceRoller

    @Before
    fun setUp() {
        diceRoller = mock()
        val players = InMemoryPlayers()
        game = HttpGooseGame(port, GooseGame(players, diceRoller)).start()
    }

    @After
    fun tearDown() {
        game.close()
    }

    @Test
    fun `not found route`() {
        given()
            .port(port)
            .contentType("application/json")
            .post("/not/found")
            .then()
            .statusCode(404)
            .contentType("application/json")
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
        addPlayer("Pippo")

        given()
            .port(port)
            .contentType("application/json")
            .body("{\"name\":\"Pippo\"}")
            .post("/players/add")
            .then()
            .statusCode(409)
            .contentType("application/json")
            .body("alreadyExists", equalTo("Pippo: Already existing player"))
    }

    @Test
    fun `moves a player from starting point`() {
        whenever(diceRoller.roll()).thenReturn(Dice(1, 2))
        addPlayer("Pluto")

        given()
            .port(port)
            .contentType("application/json")
            .get("/players/Pluto/rolls")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .body("Pluto.rolls", equalTo(listOf(1, 2)))
            .body("Pluto.moves", equalTo(mapOf("from" to "Start", "to" to "3")))
            .body("Pluto.status", equalTo(""))
    }

    @Test
    fun `moves a player to win space`() {
        whenever(diceRoller.roll()).thenReturn(Dice(60, 3))
        addPlayer("Pluto")

        given()
            .port(port)
            .contentType("application/json")
            .get("/players/Pluto/rolls")
            .then()
            .statusCode(200)
            .contentType("application/json")
            .body("Pluto.rolls", equalTo(listOf(60, 3)))
            .body("Pluto.moves", equalTo(mapOf("from" to "Start", "to" to "63")))
            .body("Pluto.status", equalTo("Wins!"))
    }


    private fun addPlayer(name: String) {
        given()
            .port(port)
            .contentType("application/json")
            .body("{\"name\":\"$name\"}")
            .post("/players/add")
            .then()
            .statusCode(200)
    }
}