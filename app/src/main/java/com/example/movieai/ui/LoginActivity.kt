package com.example.movieai.ui

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movieai.R
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.movieai.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

private lateinit var binding:ActivityLoginBinding
private lateinit var auth: FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.buttonlog.setOnClickListener{
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
            finish()
        }
        val forgotpasswordtextview = findViewById<TextView>(R.id.sifreunut)
        forgotpasswordtextview.setOnClickListener {
            val myIntent = Intent(this, ForgotPassActivity::class.java)
            startActivity(myIntent)
        }

        binding.buttonsign.setOnClickListener{
            val email= binding.editTextText.text.toString()
            val pass= binding.editTextTextPassword.text.toString()
            if(email.isNotEmpty()&&pass.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                    }
            }
        }

    }



}