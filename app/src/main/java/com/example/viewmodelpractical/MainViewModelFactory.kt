package com.example.viewmodelpractical

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(var startingValue : Int) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if ( modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(startingValue) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }


}