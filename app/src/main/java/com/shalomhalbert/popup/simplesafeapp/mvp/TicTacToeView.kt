package com.shalomhalbert.popup.simplesafeapp.mvp

import android.widget.Button

interface TicTacToeView {
    fun clearBoard()
    fun setHeadline()
    fun selectSquare(button: Button, row: Int, col: Int)
}