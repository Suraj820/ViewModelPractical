package com.example.viewmodelpractical

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelpractical.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var mainViewModelFactory: MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainViewModelFactory = MainViewModelFactory(0)

        mainActivityViewModel = ViewModelProvider(this,mainViewModelFactory)[MainActivityViewModel::class.java]
        binding.textView.text = mainActivityViewModel.getLastCount().toString()

        mainActivityViewModel.countData.observe(this, Observer {
            binding.textView.text = it.toString()
        })

        binding.button.setOnClickListener {
             mainActivityViewModel.getUpdatedCounter()
        }
    }
}