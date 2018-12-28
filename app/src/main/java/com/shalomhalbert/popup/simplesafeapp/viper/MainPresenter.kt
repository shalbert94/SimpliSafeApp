package com.shalomhalbert.popup.simplesafeapp.viper


import android.databinding.ObservableArrayList
import android.databinding.ObservableInt
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.shalomhalbert.popup.simplesafeapp.TicTacToeModel

class MainPresenter(private var view: TicTacToeContracts.View?) : TicTacToeContracts.Presenter, TicTacToeContracts.InteractorOutput {

    var interactor = MainInteractor(this)
    var router = MainRouter(view as AppCompatActivity)

    override fun fetchHeadline() {
        interactor.fetchHeadline()
    }

    override fun onHeadlineFetched(headlineResId: Int) {
        view?.setHeadline(headlineResId)
    }

    override fun onNewGameClicked() {
        interactor.resetBoard()
    }

    override fun onSquareSelected(row: Int, col: Int) {
        interactor.updateBoard(row, col)
    }

    override fun onBoardUpdated(row: Int, col: Int, currentPlayer: String, headlineResId: Int) {
        view?.selectSquare(row, col, currentPlayer)
        view?.setHeadline(headlineResId)
    }

    override fun onBoardReset(headlineResId: Int) {
        view?.clearBoard()
        view?.setHeadline(headlineResId)
    }

    override fun onDestroy() {
        view = null
    }

}