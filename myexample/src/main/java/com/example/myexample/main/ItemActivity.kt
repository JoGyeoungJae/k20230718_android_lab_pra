package com.example.myexample.main

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myexample.databinding.ActivityItemBinding


class ItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemBinding
    val serviceKey =
        "Jo/F8Pswa2Ul50H9F2/iWeQFCrrF2CuVqL+0cEJJVXlLPLQ0TCqZta52lfANIq63d6lc/4VTIeQoIYEFR84pDQ=="
    val resultType = "json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 값을 받기 위해 Intent 가져오기
        val intent = intent

        // 전달된 값 가져오기
        val id = intent.getStringExtra("id")
        val TITLE = intent.getStringExtra("TITLE")
        val CNTCT_TEL = intent.getStringExtra("CNTCT_TEL")
        val LAT = intent.getStringExtra("LAT")
        val LNG = intent.getStringExtra("LNG")
        val imgpath = intent.getStringExtra("imgpath")

        binding.id.text = id
        binding.TITLE.text = TITLE
        binding.CNTCTTEL.text = CNTCT_TEL
        binding.LAT.text = LAT
        binding.LNG.text = LNG
        binding.imgpath.(imgpath)

    }
}