package com.shalomhalbert.popup.simplesafeapp.mvp

import android.databinding.ObservableArrayList
import android.databinding.ObservableInt
import android.widget.Button
import com.shalomhalbert.popup.simplesafeapp.TicTacToeModel

class MainPresenter(private var view: TicTacToeView?) {
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


    fun resetBoard() {
        ticTacToeModel.resetBoard()
        view?.clearBoard()
        view?.setHeadline()
    }

    fun insertSymbol(button: Button, row: Int, col: Int) {
        ticTacToeModel.insertSymbol(row, col)
        view?.selectSquare(button, row, col)
        view?.setHeadline()
    }

    fun onDestroy() {
        view = null
    }

}