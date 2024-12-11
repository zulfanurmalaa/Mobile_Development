package com.capstone.fall_guard.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.capstone.fall_guard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}