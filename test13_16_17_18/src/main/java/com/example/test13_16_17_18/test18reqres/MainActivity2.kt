package com.example.test13_16_17_18.test18reqres

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain2Binding
import com.example.test13_16_17_18.databinding.ActivityMain8Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain8Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //image 결과 뷰를 넣을 뷰 만들기.
        //예제1
//        Glide.with(this)
//                // 이미지의 출발지, res -> drawable
//            .load(R.drawable.lavar)
//            .into(binding.resultView)

        // 예제2
        // 파일에서 선택한 이미지 출력하기.  후처리.
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            Log.d("lsy","it.data.data의 값이 뭔가요: ${it.data?.data}")
            Glide.with(this)
                .load(it.data?.data)
                .override(200,200)
                .into(binding.resultView)

        }
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        requestLauncher.launch(intent)

    }
}