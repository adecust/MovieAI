package com.example.movieai.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.movieai.R
import com.example.movieai.databinding.ActivityForgotPassBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


private lateinit var binding: ActivityForgotPassBinding
private lateinit var auth: FirebaseAuth

class ForgotPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.button.setOnClickListener{
            val forgotmail = findViewById<TextView>(R.id.emailforgot)

            val fAuth = FirebaseAuth.getInstance()
            fAuth.sendPasswordResetEmail(forgotmail.text.toString()).addOnCompleteListener({ listener ->
                if (listener.isSuccessful) {
                    Toast.makeText(getApplicationContext(), forgotmail.text.toString(),
                        Toast.LENGTH_SHORT).show();
                } else {
                }
            })
        }

    }
}