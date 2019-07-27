package com.xpeppers.goosegame

data class GameResponse(val message: String, val type: Type) {
    enum class Type {
        OK,
        ALREADY_EXISTS
    }

    companion object {
        fun ok(message: String): GameResponse {
            return GameResponse(message, Type.OK)
        }

        fun error(message: String): GameResponse {
            return GameResponse(message, Type.ALREADY_EXISTS)
        }
    }
}
