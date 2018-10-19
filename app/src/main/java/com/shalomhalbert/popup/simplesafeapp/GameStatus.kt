package com.shalomhalbert.popup.simplesafeapp

enum class GameStatus(val flag: Int) {
    NEXT_TURN(100),
    INVALID_CHOICE(200),
    TIE(300),
    WIN(400),
    RESET(500)
}