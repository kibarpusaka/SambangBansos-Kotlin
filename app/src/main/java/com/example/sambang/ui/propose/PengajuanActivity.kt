package com.example.sambang.ui.propose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sambang.R
import com.example.sambang.databinding.ActivityPengajuanBinding

class PengajuanActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPengajuanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengajuanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}