package com.example.test13_16_17_18.test13

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test13_16_17_18.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //버튼 클릭헤서 정방향 페이징->디테일 화면으로 이동+ 데이터 이동
        binding.button1.setOnClickListener {
            //class.java 클래서 레퍼런스 타입으로 전달은 보통 내부 앱에서 젆달하는 방식
            val intent: Intent = Intent(this, DetailActivity::class.java)
            //map 처럼 키와  valeu의 형태로 데이터를 설정 및 가져오기 작업을 할 예정
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            //시스템에게 설정한 인텐트를 전달함
            //카카오톡 프로필 변경 할때 갤러리에서 사진은 한번 설정후 갤러리 종료해도 됨
            startActivity(intent)
        }

    }
}