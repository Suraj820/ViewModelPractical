package com.example.viewmodelpractical

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(private val startingValue : Int = 0) : ViewModel() {
    private var  count = MutableLiveData<Int>()
    val countData : LiveData<Int> get() = count
    fun getLastCount() : Int?{
        return count.value
    }

    fun getUpdatedCounter(){
        count.value = (count.value)?.plus(1)
    }
    fun getInitValue() : Int{
         return startingValue
    }


}