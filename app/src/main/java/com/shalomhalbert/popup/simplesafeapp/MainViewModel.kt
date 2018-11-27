package com.shalomhalbert.popup.simplesafeapp

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.Observable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.support.annotation.VisibleForTesting
import org.jetbrains.annotations.TestOnly

/**
 * ViewModel for a TicTacToe board of any size
 */
class MainViewModel : ViewModel() {

    companion object {
        const val BOARD_SIZE = 16
    }

    private val ticTacToe: TicTacToe
    val headline: ObservableInt
    val board: ObservableArrayList<ObservableArrayList<String>>

    init {
        val sideLength = Math.sqrt(BOARD_SIZE.toDouble()).toInt()
        ticTacToe = TicTacToe(sideLength)

        headline = ticTacToe.headline
        board = ticTacToe.board
    }

    fun resetBoard() = ticTacToe.resetBoard()

    fun insertSymbol(row: Int, col: Int) = ticTacToe.insertSymbol(row, col)
}