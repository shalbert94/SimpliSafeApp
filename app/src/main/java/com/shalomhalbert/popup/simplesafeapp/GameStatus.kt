package com.shalomhalbert.popup.simplesafeapp

/**
 * Signifies the current [TicTacToe] games status
 */
enum class GameStatus(val flag: Int) {
    NEXT_TURN(100),
    INVALID_CHOICE(200),
    TIE(300),
    WIN(400),
    RESET(500)
}