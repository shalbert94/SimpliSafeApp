package com.shalomhalbert.popup.simplesafeapp

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableField

/**
 * Represents the TicTacToe game
 */
class TicTacToe(private val sideLength: Int) {
    companion object {
        const val BLANK = ""
        const val X = "X"
        const val O = "O"
    }

    private var moves = 0
    val gameStatus = MutableLiveData<GameStatus>()
    var currentPlayer = ObservableField<String>(X)
    val board = ObservableArrayList<ObservableArrayList<String>>().apply {
        repeat(sideLength) {
            add(
                    ObservableArrayList<String>().apply {
                        addAll(Array(sideLength) { "" })
                    })
        }
    }

    fun resetBoard() {
        board.forEach {
            for (i in 0 until sideLength) {
                it[i] = BLANK
            }
        }
        currentPlayer.set(X)
        moves = 0
        gameStatus.postValue(GameStatus.RESET)
    }

    fun insertSymbol(row: Int, col: Int) {
        if (board[row][col] != BLANK) {
            gameStatus.postValue(GameStatus.INVALID_CHOICE)
        } else {
            board[row][col] = currentPlayer.get()
            when {
                hasWon(row, col) -> gameStatus.postValue(GameStatus.WIN) //Update headline
                ++moves == 16 -> gameStatus.postValue(GameStatus.TIE) //Update headline
                else -> {
                    gameStatus.postValue(GameStatus.NEXT_TURN)
                } //Change turns & call changePlayer()
            }
        }
    }

    fun changePlayer() {
        when (currentPlayer.get()) {
            X -> currentPlayer.set(O)
            else -> currentPlayer.set(X)
        }
    }

    fun hasWon(row: Int, col: Int): Boolean {
        return hasVerticalWin(col) || hasHorizontalWin(row) || hasBackslashWin() || hasForwardSlashWin()
                || hasCornersWin() || hasTwoByTwoWin(row, col)
    }

    private fun hasVerticalWin(col: Int): Boolean {
        for (i in 0 until board.size) {
            if (board[i][col] != currentPlayer.get()) {
                return false
            }
        }
        return true
    }

    private fun hasHorizontalWin(row: Int): Boolean {
        for (i in 0 until board.size) {
            if (board[row][i] != currentPlayer.get()) {
                return false
            }
        }
        return true
    }

    private fun hasBackslashWin(): Boolean {
        for (i in 0 until board.size) {
            if (board[i][i] != currentPlayer.get()) {
                return false
            }
        }
        return true
    }

    private fun hasForwardSlashWin(): Boolean {
        var x = board.size - 1
        for (y in 0 until board.size) {
            if (board[y][x--] != currentPlayer.get()) {
                return false
            }
        }
        return true
    }

    private fun hasCornersWin(): Boolean {
        val topLeft = board[0][0]
        val topRight = board[0][board.size - 1]
        val btmLeft = board[board.size - 1][0]
        val btmRight = board[board.size - 1][board.size - 1]

        return topLeft == currentPlayer.get()
                && topRight == currentPlayer.get()
                && btmLeft == currentPlayer.get()
                && btmRight == currentPlayer.get()
    }

    /**
     * There are 4 possible 2X2 wins in a 3X3 sub-grid around the selected position.
     */
    private fun hasTwoByTwoWin(row: Int, col: Int): Boolean {
        //Top left
        if (row > 0 && col > 0) {
            if (board[row - 1][col] == currentPlayer.get() //Above
                    && board[row - 1][col - 1] == currentPlayer.get()  //Above and left
                    && board[row][col - 1] == currentPlayer.get()) { // Left
                return true
            }
        }
        //Bottom left
        if (row < board.size - 1 && col > 0) {
            if (board[row][col - 1] == currentPlayer.get() //Left
                    && board[row + 1][col - 1] == currentPlayer.get() //Below and left
                    && board[row + 1][col] == currentPlayer.get()) { //Below
                return true
            }
        }

        //Top right
        if (row > 0 && col < board.size - 1) {
            if (board[row - 1][col] == currentPlayer.get() //Above
                    && board[row - 1][col + 1] == currentPlayer.get() //Above and right
                    && board[row][col + 1] == currentPlayer.get()) {  //Right
                return true
            }
        }

        //Bottom right
        if (row < board.size - 1 && col < board.size - 1) {
            if (board[row][col + 1] == currentPlayer.get()  // Right
                    && board[row + 1][col + 1] == currentPlayer.get()  //Below and right
                    && board[row + 1][col] == currentPlayer.get()) { //Below
                return true
            }
        }
        return false
    }
}