package com.example.test13_16_17_18.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain408Binding

class MainActivity408 : AppCompatActivity() {
    lateinit var binding:ActivityMain408Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain408Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //후처리 결과는 동일, 과정이 다름
        //구글 측에서 이함수를 사용해주세요 권고
        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            //  ActivityResultContracts.StartActivityForResult())
            //이 부분이 시스템에서 제공해 주고있음
            //ex) StartActivityfForResult() 후처리
            //ex) TakePicture :  사진 촬영 저장 비트맵 획득
            //ex) RequestPermission: 권한 요청 허락 여부 파악
            ActivityResultContracts.StartActivityForResult())
        {
            //it, 후처리 결과 객체
            val resultData = it.data?.getStringExtra("result")
            binding.mainResultView.text = "result : $resultData"
        }

        binding.button1.setOnClickListener {

            val intent: Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            //여기서, 후처리 함수를 호출합니다. 현재 1번 메인->2번 디테일
            //2번 디테일 화면에서 데이러를 잘 가공해서 돌려주면 registerForActivityResult 돌아가게된다.
            requestLauncher.launch(intent)
        }
    }
}