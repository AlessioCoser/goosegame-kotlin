package com.xpeppers.goosegame

data class Command(val name: String, val type: Type) {
    enum class Type {
        NOT_FOUND
    }

    companion object {
        fun notFound() = Command("", Type.NOT_FOUND)
    }
}
