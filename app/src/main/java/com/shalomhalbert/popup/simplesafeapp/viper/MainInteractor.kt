package com.shalomhalbert.popup.simplesafeapp.viper

import android.databinding.ObservableArrayList
import android.databinding.ObservableInt
import com.shalomhalbert.popup.simplesafeapp.TicTacToeModel

class MainInteractor(var output: TicTacToeContracts.InteractorOutput?): TicTacToeContracts.Interactor {

    companion object {
        private const val BOARD_SIZE = 16
    }

    private val ticTacToeModel: TicTacToeModel

    init {
        val sideLength = Math.sqrt(BOARD_SIZE.toDouble()).toInt()
        ticTacToeModel = TicTacToeModel(sideLength)
    }

    override fun resetBoard() {
        ticTacToeModel.resetBoard()
        output?.onBoardReset(ticTacToeModel.headline.get())
    }

    override fun updateBoard(row: Int, col: Int) {
        val previousPlayer = ticTacToeModel.currentPlayer.get()!!
        ticTacToeModel.insertSymbol(row, col)
        output?.onBoardUpdated(row, col, previousPlayer, ticTacToeModel.headline.get())
    }

    override fun fetchHeadline() {
        output?.onHeadlineFetched(ticTacToeModel.headline.get())
    }

}