package com.shalomhalbert.popup.simplesafeapp.viper

import android.databinding.ObservableArrayList
import android.widget.Button

interface TicTacToeContracts {
    interface View {
        fun setHeadline(headlineResId: Int)
        fun clearBoard()
        fun selectSquare(row: Int, col: Int, currentPlayer: String)
    }

    interface Presenter {
        fun onDestroy()
        fun onNewGameClicked()
        fun onSquareSelected(row: Int, col: Int)
        fun fetchHeadline()
    }

    interface Interactor {
        fun resetBoard()
        fun updateBoard(row: Int, col: Int)
        fun fetchHeadline()
    }

    interface InteractorOutput {
        fun onBoardUpdated(row: Int, col: Int, currentPlayer: String, headlineResId: Int)
        fun onBoardReset(headlineResId: Int)
        fun onHeadlineFetched(headlineResId: Int)
    }

    interface Router {
        fun changeScreen()
    }
}

//{
//    interface View {
//        fun clearBoard()
//        fun setHeadline()
//        fun selectSquare(button: Button, row: Int, col: Int)
//    }
//
//    interface Presenter {
//        fun onNewGameClicked()
//        fun onSquareSelected()
//    }
//
//    interface Interactor {
//
//    }
//}