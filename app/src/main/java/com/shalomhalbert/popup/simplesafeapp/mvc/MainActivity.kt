package com.shalomhalbert.popup.simplesafeapp.mvc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.shalomhalbert.popup.simplesafeapp.R
import com.shalomhalbert.popup.simplesafeapp.TicTacToeModel
import kotlinx.android.synthetic.main.activity_main_mvc.*

class MainActivity : AppCompatActivity() {
    private val BOARD_SIZE = 16

    private val ticTacToe by lazy {
        val sideLength = Math.sqrt(BOARD_SIZE.toDouble()).toInt()
        TicTacToeModel(sideLength)
    }

    private val headlineText
        get() = resources.getString(ticTacToe.headline.get())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_mvc)


        val headline = findViewById<TextView>(R.id.headline)
        headline.text = headlineText

        setOnClickListeners()
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
            ticTacToe.resetBoard()
            headline.text = headlineText
            resetSquares()
        }
    }

    private fun handleSquareClick(button: Button, row: Int, col: Int) {
        button.setOnClickListener {
            insertSymbol(row, col)
            button.text = ticTacToe.board[row][col]
        }
    }

    private fun resetSquares() {
        findViewById<Button>(R.id.s00).text = ticTacToe.board[0][0]
        findViewById<Button>(R.id.s01).text = ticTacToe.board[0][1]
        findViewById<Button>(R.id.s02).text = ticTacToe.board[0][2]
        findViewById<Button>(R.id.s03).text = ticTacToe.board[0][3]
        findViewById<Button>(R.id.s10).text = ticTacToe.board[1][0]
        findViewById<Button>(R.id.s11).text = ticTacToe.board[1][1]
        findViewById<Button>(R.id.s12).text = ticTacToe.board[1][2]
        findViewById<Button>(R.id.s13).text = ticTacToe.board[1][3]
        findViewById<Button>(R.id.s20).text = ticTacToe.board[2][0]
        findViewById<Button>(R.id.s21).text = ticTacToe.board[2][1]
        findViewById<Button>(R.id.s22).text = ticTacToe.board[2][2]
        findViewById<Button>(R.id.s23).text = ticTacToe.board[2][3]
        findViewById<Button>(R.id.s30).text = ticTacToe.board[3][0]
        findViewById<Button>(R.id.s31).text = ticTacToe.board[3][1]
        findViewById<Button>(R.id.s32).text = ticTacToe.board[3][2]
        findViewById<Button>(R.id.s33).text = ticTacToe.board[3][3]
    }

    private fun insertSymbol(row: Int, col: Int) {
        ticTacToe.insertSymbol(row, col)
        headline.text = headlineText
    }

}
