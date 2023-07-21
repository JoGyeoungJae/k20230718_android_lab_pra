package com.example.test6_joj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test6_joj.databinding.ActivityLoginBinding
import com.example.test6_joj.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        //뷰 바인딩 적용
        setContentView(binding.root)

        binding.submitBtm.setOnClickListener{
            val id :String = binding.inputid.toString();
            val pw: String = binding.inputpw.toString();


        }
    }
}