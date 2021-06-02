package com.example.sambang.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sambang.MainActivity
import com.example.sambang.R
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}