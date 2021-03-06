package org.runestar.client.game.api

enum class GameState(val id: Int) {

    NONE(0),
    STARTING(5),
    TITLE(10),
    AUTHENTICATOR(11),
    LOGGING_IN(20),
    LOADING(25),
    LOGGED_IN(30),
    CONNECTION_LOST(40),
    CHANGING_WORLD(45);

    companion object {

        @JvmField val LOOKUP = values().associateBy { it.id }

        fun of(id: Int): GameState {
            return LOOKUP.getValue(id)
        }
    }
}