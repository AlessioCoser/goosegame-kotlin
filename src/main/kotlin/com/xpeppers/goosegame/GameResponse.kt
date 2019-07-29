package com.xpeppers.goosegame

sealed class GameResponse {
    data class PrankResponse(val name: String, val beginPosition: Int, val landPosition: Int, val dice: Dice, val other: Player): GameResponse()
    data class GooseResponse(val name: String, val beginPosition: Int, val landPosition: Int, val dice: Dice, val moves: MutableList<Int>): GameResponse()
    data class BridgeResponse(val name: String, val beginPosition: Int, val landPosition: Int, val dice: Dice): GameResponse()
    data class WinResponse(val name: String, val beginPosition: Int, val landPosition: Int, val dice: Dice): GameResponse()
    data class BounceResponse(val name: String, val beginPosition: Int, val landPosition: Int, val dice: Dice, val winSpace: Int): GameResponse()
    data class NormalResponse(val name: String, val beginPosition: Int, val landPosition: Int, val dice: Dice): GameResponse()
    data class AlreadyExistsResponse(val name: String): GameResponse()
    data class PlayersResponse(val names: List<String>): GameResponse()
    data class NotFoundResponse(val command: String): GameResponse()
}