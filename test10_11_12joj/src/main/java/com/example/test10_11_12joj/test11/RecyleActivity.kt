package com.example.test10_11_12joj.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10_11_12joj.adapter.MyAdapter
import com.example.test10_11_12joj.databinding.ActivityRecyleBinding

//리사이클러 뷰를 출력해주는 빈 도화지
class RecyleActivity : AppCompatActivity() {
    lateinit var  binding: ActivityRecyleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //임시 더미 데이터, 현재 텍스트 뷰만 9 개
        val datas = mutableListOf<String>()
        for(i in 1..20){
            datas.add("Item $i")
        }
        //옵션에서 출력의 모양을 정하는 부분
        //보통 리니어 세로 가로 방향으로 출력하고
//        표 형식 지그재그로 표현 옵션이 있음
//                구현 옵션만 변경해서 확인
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = MyAdapter(datas)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL)
        )
        //순서 구성요소
        //1) 뷰 홀더 생성 : 어댑터 내부에 정의가 되어 있음  ->MyViewHolder
        //2) 어댑터 생성 : 기존 샘플 코드는 액티비티 내부 클래스로 구성 -> 따로 분리 ->MyAdapter
        //3) 설정한 리사이클러 뷰를 메인(RecyleActivity)에 정용함
        //4) 임시 데이터(리스트), 원래 공공 데이터등 디비에서 받아온 데이터 사용
    }
}