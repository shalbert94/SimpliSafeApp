package com.shalomhalbert.popup.simplesafeapp

import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Tests if [TicTacToeModel]'s hasWon() checks are accurate
 */
class TicTacToeModelTest {
    @Test fun `hasWon() returns true for vertical win`() {
        //Given a board with side length of 4 and the column being populated
        val ticTacToe = TicTacToeModel(4)
        val col = 2

        //When insertions are made in a vertical row
        ticTacToe.board.forEach {
            it[col] = TicTacToeModel.X
        }

        //Then
        assertThat(ticTacToe.hasWon(0, col)).isTrue()
    }

    @Test fun `hasWon() returns true for horizontal win`() {
        //Given a board with side length of 4 and the row being populated
        val ticTacToe = TicTacToeModel(4)
        val row = 2

        //When insertions are made in a horizontal row
        for (col in 0 until 4) ticTacToe.board[row][col] = TicTacToeModel.X

        //Then
        assertThat(ticTacToe.hasWon(row, 2)).isTrue()
    }

    @Test fun `hasWon() returns true for backslash win`() {
        //Given a board with side length of 4
        val ticTacToe = TicTacToeModel(4)

        //When insertions are made in a backslash pattern
        for (i in 0 until 4) ticTacToe.board[i][i] = TicTacToeModel.X

        //Then
        assertThat(ticTacToe.hasWon(3, 2)).isTrue()
    }

    @Test fun `hasWon() returns true for forward-slash win`() {
        //Given a board with side length of 4 and the starting column
        val ticTacToe = TicTacToeModel(4)
        var col = ticTacToe.board.size - 1

        //When insertions are made in a forward-slash pattern
        for (i in 0 until 4) ticTacToe.board[i][col--] = TicTacToeModel.X

        //Then
        assertThat(ticTacToe.hasWon(3, 2)).isTrue()
    }

    @Test fun `hasWon() returns true for corner win`() {
        //Given a board with side length of 4
        val ticTacToe = TicTacToeModel(4)

        //When insertions are made in a corner pattern
        ticTacToe.board[0][0] = TicTacToeModel.X
        ticTacToe.board[0][ticTacToe.board.size - 1] = TicTacToeModel.X
        ticTacToe.board[ticTacToe.board.size - 1][0] = TicTacToeModel.X
        ticTacToe.board[ticTacToe.board.size - 1][ticTacToe.board.size - 1] = TicTacToeModel.X

        //Then
        assertThat(ticTacToe.hasWon(3, 2)).isTrue()
    }

    @Test fun `hasWon() returns true for 2X2 win`() {
        //Given a board with side length of 4
        val ticTacToe = TicTacToeModel(4)

        //When insertions are made in a 2X2 pattern
        ticTacToe.board[0][0] = TicTacToeModel.X
        ticTacToe.board[0][1] = TicTacToeModel.X
        ticTacToe.board[1][0] = TicTacToeModel.X
        ticTacToe.board[1][1] = TicTacToeModel.X

        //Then
        assertThat(ticTacToe.hasWon(1, 0)).isTrue()
    }

    @Test fun `hasWon() returns false`() {
        //Given a board with side length of 4
        val ticTacToe = TicTacToeModel(4)

        //When insertions are made in a 2X2 pattern
        ticTacToe.board[0][0] = TicTacToeModel.O
        ticTacToe.board[0][1] = TicTacToeModel.X
        ticTacToe.board[1][0] = TicTacToeModel.X
        ticTacToe.board[1][1] = TicTacToeModel.X

        //Then
        assertThat(ticTacToe.hasWon(1, 0)).isFalse()
    }
}