package com.shalomhalbert.popup.simplesafeapp

import com.google.common.truth.Truth.assertThat
import com.shalomhalbert.popup.simplesafeapp.mvvm.MainViewModel
import org.junit.Test

class MainViewModelTest {
    @Test
    fun `headline is correct value`() {
        //Given
        val mainViewModel = MainViewModel()
        //When
        val number = 3433
        mainViewModel.headline.set(number)
        //Then
        assertThat(mainViewModel.headline.get()).isEqualTo(number)
    }

    @Test
    fun `board has correct values`() {
        //Given
        val mainViewModel = MainViewModel()
        //When
        val text = "Hello"
        mainViewModel.board[0][0] = text
        mainViewModel.board[1][0] = text
        mainViewModel.board[0][3] = text
        //Then
        assertThat(mainViewModel.board[0][0]).isSameAs(text)
        assertThat(mainViewModel.board[1][0]).isSameAs(text)
        assertThat(mainViewModel.board[0][3]).isSameAs(text)
        assertThat(mainViewModel.board[3][0]).isNotSameAs(text)
        assertThat(mainViewModel.board[0][2]).isNotSameAs(text)
    }

    @Test
    fun `board resets correctly`() {
        //Given
        val mainViewModel = MainViewModel()
        //When
        val text = "Hello"
        mainViewModel.board[0][0] = text
        mainViewModel.board[1][0] = text
        mainViewModel.board[0][3] = text
        //Then
        mainViewModel.resetBoard()
        assertThat(mainViewModel.board[0][0]).isNotSameAs(text)
        assertThat(mainViewModel.board[1][0]).isNotSameAs(text)
        assertThat(mainViewModel.board[0][3]).isNotSameAs(text)
        assertThat(mainViewModel.board[3][0]).isNotSameAs(text)
        assertThat(mainViewModel.board[0][2]).isNotSameAs(text)
    }

    @Test
    fun `board inserts correct values`() {
        //Given
        val mainViewModel = MainViewModel()
        //When
        val text = "Hello"
        mainViewModel.insertSymbol(0,0)
        mainViewModel.insertSymbol(1,0)
        mainViewModel.insertSymbol(0,3)
        //Then
        assertThat(mainViewModel.board[0][0]).isSameAs(TicTacToeModel.X)
        assertThat(mainViewModel.board[1][0]).isSameAs(TicTacToeModel.O)
        assertThat(mainViewModel.board[0][3]).isSameAs(TicTacToeModel.X)
        assertThat(mainViewModel.board[3][0]).isSameAs(TicTacToeModel.BLANK)
        assertThat(mainViewModel.board[0][2]).isSameAs(TicTacToeModel.BLANK)
    }

}