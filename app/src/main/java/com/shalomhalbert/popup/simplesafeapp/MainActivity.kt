package com.shalomhalbert.popup.simplesafeapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shalomhalbert.popup.simplesafeapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    companion object {
        const val BOARD_SIZE = 16
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.newGameButton.setOnClickListener { viewModel.resetBoard() }
        binding.table.vm = viewModel

        subscribeToObserver(viewModel, binding)
    }

    private fun subscribeToObserver(viewModel: MainViewModel, binding: ActivityMainBinding) {
        viewModel.gameStatus.observe(this, Observer {
            when (it) {
                GameStatus.NEXT_TURN -> {
                    viewModel.changePlayer()

                    when (viewModel.currentPlayer) {
                        MainViewModel.X -> binding.headline.text = getString(R.string.headline_current_player_X)
                        MainViewModel.O -> binding.headline.text = getString(R.string.headline_current_player_O)
                    }
                }
                GameStatus.INVALID_CHOICE -> Toast.makeText(this,
                        getString(R.string.invalid_choice_error_message), Toast.LENGTH_SHORT).show()
                GameStatus.TIE -> binding.headline.text = getString(R.string.headline_tie)
                GameStatus.WIN -> {
                    when (viewModel.currentPlayer) {
                        MainViewModel.X -> binding.headline.text = getString(R.string.headline_winner_x)
                        MainViewModel.O -> binding.headline.text = getString(R.string.headline_winner_o)
                    }
                }
                GameStatus.RESET -> binding.headline.text = getString(R.string.headline_current_player_X)
            }
        })
    }
}
