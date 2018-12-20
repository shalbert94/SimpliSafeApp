package com.shalomhalbert.popup.simplesafeapp.mvvm

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableInt
import com.shalomhalbert.popup.simplesafeapp.TicTacToeModel

/**
 * ViewModel for a TicTacToeModel board of any size
 */
class MainViewModel : ViewModel() {

    companion object {
        const val BOARD_SIZE = 16
    }

    private val ticTacToeModel: TicTacToeModel
    val headline: ObservableInt
    val board: ObservableArrayList<ObservableArrayList<String>>

    init {
        val sideLength = Math.sqrt(BOARD_SIZE.toDouble()).toInt()
        ticTacToeModel = TicTacToeModel(sideLength)

        headline = ticTacToeModel.headline
        board = ticTacToeModel.board
    }

    fun resetBoard() = ticTacToeModel.resetBoard()

    fun insertSymbol(row: Int, col: Int) = ticTacToeModel.insertSymbol(row, col)
}