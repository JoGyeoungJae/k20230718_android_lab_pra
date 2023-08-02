package com.example.test10_11_12joj.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test10_11_12joj.databinding.ActivityDetailBinding
import com.example.test10_11_12joj.databinding.ActivityNotiBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}