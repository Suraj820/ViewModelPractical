package com.example.viewmodelpractical

import androidx.lifecycle.ViewModel

class MainActivityViewModel(private val startingValue : Int = 0) : ViewModel() {
 private var  count = 0;

    fun getLastCount() : Int{
        return count
    }

    fun getUpdatedCounter() : Int{
        return ++count
    }
    fun getInitValue() : Int{
         return startingValue
    }


}