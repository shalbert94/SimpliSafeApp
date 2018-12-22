package com.shalomhalbert.popup.simplesafeapp.mvvm

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shalomhalbert.popup.simplesafeapp.R
import com.shalomhalbert.popup.simplesafeapp.databinding.ActivityMainMvvmBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_mvvm)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityMainMvvmBinding>(this, R.layout.activity_main_mvvm)

        binding.table.vm = viewModel
        binding.vm = viewModel
    }

}