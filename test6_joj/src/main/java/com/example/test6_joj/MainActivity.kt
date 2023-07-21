package com.example.test6_joj

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.test6_joj.databinding.ActivityMainBinding

//액티비티: 화면을 그려주는 역할
class MainActivity : AppCompatActivity() {
    //최초에 한번 화면에 출력하는 역할
    override fun onCreate(savedInstanceState: Bundle?) {
        //savedInstanceState :Bundle? -> 임시 데이터 저장 책체
        super.onCreate(savedInstanceState)
        //setContentView 레이아웃 샘플로 리니어 사용하는 중, 이유? 제약조건 설정이 간단해서
        //뷰만 선택해서 배치만하면, 기본세로, 가로방향으로 배치가 간단함
        setContentView(R.layout.activity_main)
        //뷰 바인딩적용
        //시스템에서 자동으로 적용 ActivityMainBinding 여기 코드,
        // 설정 코드이기 때문에 디비 연결하는 connection 객체 용도가 비슷
        //binding 작업 하는 뷰가 몯 들어가 있다.
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //뷰 바인딩 적용
        setContentView(binding.root)
        //버튼 클릭 -> 이미지 -> show/hide
        //버튼에 이벤트 클릭 리스너 설정후 결과로 이미지 속성중에
        //visibility  속성으로 show/hide
        //vinding 객체 안에  사용하는 뷰의 모든 객체가 다 담아져 있습니다.

        binding.visibleView.setOnClickListener{
            if(binding.targetView.visibility== View.VISIBLE) {
                binding.targetView.visibility = View.GONE
            }else if(binding.targetView.visibility== View.GONE){
                binding.targetView.visibility =View.VISIBLE
            }

        }
        binding.visibleView2.setOnClickListener{
            binding.targetView.visibility =View.VISIBLE
        }
        binding.loginBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.joinBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, JoinActivity::class.java)
            startActivity(intent)
        }
    }
}