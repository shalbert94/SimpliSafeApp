package com.shalomhalbert.popup.simplesafeapp

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.support.annotation.VisibleForTesting
import org.jetbrains.annotations.TestOnly

/**
 * ViewModel for a TicTacToe board of any size
 */
class MainViewModel : ViewModel() {
    private val ticTacToe: TicTacToe

    val gameStatus: MutableLiveData<GameStatus>
    var currentPlayer:ObservableField<String>
    val board: ObservableArrayList<ObservableArrayList<String>>

    init {
        val sideLength = Math.sqrt(MainActivity.BOARD_SIZE.toDouble()).toInt()
        ticTacToe = TicTacToe(sideLength)

        gameStatus = ticTacToe.gameStatus
        currentPlayer = ticTacToe.currentPlayer
        board = ticTacToe.board
    }

    fun resetBoard() = ticTacToe.resetBoard()

    fun changePlayer() = ticTacToe.changePlayer()

    fun insertSymbol(row: Int, col: Int) = ticTacToe.insertSymbol(row, col)
}