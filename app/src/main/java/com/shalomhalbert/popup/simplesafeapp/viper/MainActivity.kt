package com.shalomhalbert.popup.simplesafeapp.viper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.shalomhalbert.popup.simplesafeapp.R
import com.shalomhalbert.popup.simplesafeapp.TicTacToeModel
import kotlinx.android.synthetic.main.activity_main_viper.*
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), TicTacToeContracts.View {

    private var presenter: TicTacToeContracts.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_viper)

        presenter = MainPresenter(this)

        setOnClickListeners()
        presenter?.fetchHeadline()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy() //Prevents view access after onDestroy() is called
        presenter = null
    }

    override fun clearBoard() {
        resetSquares()
    }

    override fun setHeadline(headline: Int) {
        headlineTextView.text = getString(headline)
    }

    override fun selectSquare(row: Int, col: Int, currentPlayer: String) {
        getSquare(row, col).text = currentPlayer
    }

    private fun setOnClickListeners() {
        for (row in 0..3) for (col in 0..3) {
            val square = getSquare(row, col)
            handleSquareClick(square, row, col)
        }

        findViewById<Button>(R.id.newGameButton).setOnClickListener {
            presenter?.onNewGameClicked()
        }
    }

    private fun handleSquareClick(square: Button, row: Int, col: Int) {
        square.setOnClickListener {
            presenter?.onSquareSelected(row, col)
        }
    }

    private fun resetSquares() {
        for (row in 0..3) for (col in 0..3) {
            val square = getSquare(row, col)
            square.text = TicTacToeModel.BLANK
        }
    }

    private fun getSquare(row: Int, col: Int): Button = when {
        row == 0 && col == 0 -> findViewById(R.id.s00)
        row == 0 && col == 1 -> findViewById(R.id.s01)
        row == 0 && col == 2 -> findViewById(R.id.s02)
        row == 0 && col == 3 -> findViewById(R.id.s03)
        row == 1 && col == 0 -> findViewById(R.id.s10)
        row == 1 && col == 1 -> findViewById(R.id.s11)
        row == 1 && col == 2 -> findViewById(R.id.s12)
        row == 1 && col == 3 -> findViewById(R.id.s13)
        row == 2 && col == 0 -> findViewById(R.id.s20)
        row == 2 && col == 1 -> findViewById(R.id.s21)
        row == 2 && col == 2 -> findViewById(R.id.s22)
        row == 2 && col == 3 -> findViewById(R.id.s23)
        row == 3 && col == 0 -> findViewById(R.id.s30)
        row == 3 && col == 1 -> findViewById(R.id.s31)
        row == 3 && col == 2 -> findViewById(R.id.s32)
        row == 3 && col == 3 -> findViewById(R.id.s33)
        else -> throw IllegalArgumentException("$row ir $col is an invalid number")
    }
}
