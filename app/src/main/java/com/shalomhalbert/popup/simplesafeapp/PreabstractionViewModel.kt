package com.shalomhalbert.popup.simplesafeapp

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList

/**
 * ViewModel for a Tic Tac Toe board of any size
 */
class PreabstractionViewModel : ViewModel() {
    companion object {
        const val BLANK = ""
        const val X = "X"
        const val O = "O"
    }

    private val sideLength = Math.sqrt(16.toDouble()).toInt()
    private var moves = 0
    val gameStatus = MutableLiveData<GameStatus>()
    var currentPlayer = X
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
        currentPlayer = X
        moves = 0
        gameStatus.postValue(GameStatus.RESET)
    }

    fun insertSymbol(row: Int, col: Int) {
        if (board[row][col] != BLANK) {
//            gameStatus.postValue(GameStatus.INVALID_CHOICE)
        } else {
            board[row][col] = currentPlayer
            when {
                hasWon(row, col) -> gameStatus.postValue(GameStatus.WIN) //Update headline
                ++moves == 16 -> gameStatus.postValue(GameStatus.TIE) //Update headline
                else -> { gameStatus.postValue(GameStatus.NEXT_TURN) } //Change turns & call changePlayer()
            }
        }
    }

    fun changePlayer() {
        currentPlayer = when (currentPlayer) {
            X -> O
            else -> X
        }
    }

    private fun hasWon(row: Int, col: Int): Boolean {
        return hasVerticalWin(col) || hasHorizontalWin(row) || hasBackslashWin() || hasForwardSlashWin()
                || hasCornersWin() || hasTwoByTwoWin(row, col)
    }

    private fun hasVerticalWin(col: Int): Boolean {
        for (i in 0 until board.size) {
            if (board[i][col] != currentPlayer) {
                return false
            }
        }
        return true
    }

    private fun hasHorizontalWin(row: Int): Boolean {
        for (i in 0 until board.size) {
            if (board[row][i] != currentPlayer) {
                return false
            }
        }
        return true
    }

    private fun hasBackslashWin(): Boolean {
        for (i in 0 until board.size) {
            if (board[i][i] != currentPlayer) {
                return false
            }
        }
        return true
    }

    private fun hasForwardSlashWin(): Boolean {
        var x = board.size - 1
        for (y in 0 until board.size) {
            if (board[y][x--] != currentPlayer) {
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

        return topLeft == currentPlayer
                && topRight == currentPlayer
                && btmLeft == currentPlayer
                && btmRight == currentPlayer
    }

    /**
     * There are 4 possible 2X2 wins in a 3X3 sub-grid around the selected position.
     */
    private fun hasTwoByTwoWin(row: Int, col: Int): Boolean {
        //Top left
        if (row > 0 && col > 0) {
            if (board[row - 1][col] == currentPlayer //Above
                    && board[row - 1][col - 1] == currentPlayer  //Above and left
                    && board[row][col - 1] == currentPlayer) { // Left
                return true
            }
        }
        //Bottom left
        if (row < board.size - 1 && col > 0) {
            if (board[row][col - 1] == currentPlayer //Left
                    && board[row + 1][col - 1] == currentPlayer //Below and left
                    && board[row + 1][col] == currentPlayer) { //Below
                return true
            }
        }

        //Top right
        if (row > 0 && col < board.size - 1) {
            if (board[row - 1][col] == currentPlayer //Above
                    && board[row - 1][col + 1] == currentPlayer //Above and right
                    && board[row][col + 1] == currentPlayer) {  //Right
                return true
            }
        }

        //Bottom right
        if (row < board.size - 1 && col < board.size - 1) {
            if (board[row][col + 1] == currentPlayer  // Right
                    && board[row + 1][col + 1] == currentPlayer  //Below and right
                    && board[row + 1][col] == currentPlayer) { //Below
                return true
            }
        }
        return false
    }
}