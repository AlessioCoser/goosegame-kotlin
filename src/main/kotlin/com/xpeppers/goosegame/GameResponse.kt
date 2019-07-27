package com.xpeppers.goosegame

data class GameResponse(val message: String, val type: Type) {
    enum class Type {
        OK
    }

    companion object {
        fun ok(message: String): GameResponse {
            return GameResponse(message, Type.OK)
        }
    }
}
