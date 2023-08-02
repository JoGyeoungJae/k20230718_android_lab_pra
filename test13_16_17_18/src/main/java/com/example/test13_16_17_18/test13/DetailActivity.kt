package com.example.test13_16_17_18.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("kkang","DetailActivity..onCreate..........")
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //인텐트로 전달 받은 값을 가져오는 역할. getter
        //가져오는 메서드들은 타입이 각각 있음 지금은 문자열 정수만 연습하면 될것같음

        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getIntExtra("data2", 0)

        binding.detailResultView.text = "data1: $data1, data2: $data2"
        //후처리는 메인-> 디테일로 갔다면, 메인<- 디테일 진행 역방향
        binding.detailButton.setOnClickListener {
            intent.putExtra("result","world")
            setResult(RESULT_OK, intent)
            finish()
        }



    }
}