package com.xpeppers.goosegame

data class GameResponse(val message: String, val type: Type) {
    enum class Type {
        OK,
        ALREADY_EXISTS,
        NOT_FOUND
    }

    companion object {
        fun ok(message: String): GameResponse {
            return GameResponse(message, Type.OK)
        }

        fun alreadyExists(message: String): GameResponse {
            return GameResponse(message, Type.ALREADY_EXISTS)
        }

        fun notFound(message: String): GameResponse {
            return GameResponse(message, Type.NOT_FOUND)
        }
    }
}
