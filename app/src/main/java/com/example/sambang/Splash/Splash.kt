package com.example.sambang.Splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.sambang.Login.Login
import com.example.sambang.MainActivity
import com.example.sambang.R

class Splash : AppCompatActivity() {
    private val SPLASH_TIME : Long = 700
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        @Suppress("DEPRECATION")
        Handler().postDelayed({
            startActivity(Intent(this, Login::class.java))
            finish()
        }, SPLASH_TIME

        )
    }
}