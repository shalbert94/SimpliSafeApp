package com.shalomhalbert.popup.simplesafeapp.mvp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.shalomhalbert.popup.simplesafeapp.R
import com.shalomhalbert.popup.simplesafeapp.TicTacToeModel
import kotlinx.android.synthetic.main.activity_main_mvp.*

class MainActivity : AppCompatActivity(), TicTacToeView {
    private val presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_mvp)
        setHeadline()

        setOnClickListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy() //Prevents view access after onDestroy() is called
    }

    override fun clearBoard() {
        resetSquares()
    }

    override fun setHeadline() {
        headline.text = getString(presenter.headline.get())
    }

    override fun selectSquare(button: Button, row: Int, col: Int) {
        button.text = presenter.board[row][col]
    }

    private fun setOnClickListeners() {
        handleSquareClick(findViewById(R.id.s00), 0, 0)
        handleSquareClick(findViewById(R.id.s01), 0, 1)
        handleSquareClick(findViewById(R.id.s02), 0, 2)
        handleSquareClick(findViewById(R.id.s03), 0, 3)
        handleSquareClick(findViewById(R.id.s10), 1, 0)
        handleSquareClick(findViewById(R.id.s11), 1, 1)
        handleSquareClick(findViewById(R.id.s12), 1, 2)
        handleSquareClick(findViewById(R.id.s13), 1, 3)
        handleSquareClick(findViewById(R.id.s20), 2, 0)
        handleSquareClick(findViewById(R.id.s21), 2, 1)
        handleSquareClick(findViewById(R.id.s22), 2, 2)
        handleSquareClick(findViewById(R.id.s23), 2, 3)
        handleSquareClick(findViewById(R.id.s30), 3, 0)
        handleSquareClick(findViewById(R.id.s31), 3, 1)
        handleSquareClick(findViewById(R.id.s32), 3, 2)
        handleSquareClick(findViewById(R.id.s33), 3, 3)

        findViewById<Button>(R.id.newGameButton).setOnClickListener {
            presenter.resetBoard()
        }
    }

    private fun handleSquareClick(button: Button, row: Int, col: Int) {
        button.setOnClickListener {
            presenter.insertSymbol(button, row, col)
        }
    }

    private fun resetSquares() {
        findViewById<Button>(R.id.s00).text = presenter.board[0][0]
        findViewById<Button>(R.id.s01).text = presenter.board[0][1]
        findViewById<Button>(R.id.s02).text = presenter.board[0][2]
        findViewById<Button>(R.id.s03).text = presenter.board[0][3]
        findViewById<Button>(R.id.s10).text = presenter.board[1][0]
        findViewById<Button>(R.id.s11).text = presenter.board[1][1]
        findViewById<Button>(R.id.s12).text = presenter.board[1][2]
        findViewById<Button>(R.id.s13).text = presenter.board[1][3]
        findViewById<Button>(R.id.s20).text = presenter.board[2][0]
        findViewById<Button>(R.id.s21).text = presenter.board[2][1]
        findViewById<Button>(R.id.s22).text = presenter.board[2][2]
        findViewById<Button>(R.id.s23).text = presenter.board[2][3]
        findViewById<Button>(R.id.s30).text = presenter.board[3][0]
        findViewById<Button>(R.id.s31).text = presenter.board[3][1]
        findViewById<Button>(R.id.s32).text = presenter.board[3][2]
        findViewById<Button>(R.id.s33).text = presenter.board[3][3]
    }

}
