package com.example.movieai.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movieai.R
import com.example.movieai.databinding.ActivityLoginBinding
import com.example.movieai.databinding.ActivityMainMenuBinding

private lateinit var binding: ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}