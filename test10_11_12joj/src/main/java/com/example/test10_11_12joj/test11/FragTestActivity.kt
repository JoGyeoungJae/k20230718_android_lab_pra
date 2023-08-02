package com.example.test10_11_12joj.test11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.test10_11_12joj.R
import com.example.test10_11_12joj.databinding.ActivityFragTestBinding
import com.example.test10_11_12joj.fragment.OneFragment

class FragTestActivity : AppCompatActivity() {
    lateinit var binding:ActivityFragTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragTestBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        프래그먼트 기본 테스트
//        방법 1 xml  구성하는 방법 2. 코드로 구성하는 방법
//        방법 1
//        출력방식이 액티비티에 name으로 해당 프래그먼트를 지정해서 출력
//
//        방법 2
//          액티비티 코드에서, 해당 프래그먼트를 호출 출력하는 방법
        //TwoFragment.kt 만들어서 구현
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        val fragment = OneFragment()

        transaction.add(R.id.fragment_content, fragment)
        //백스탭을 설정시 커밋 이전 함수에서 설정 하면 딤
//        뒤로가기 버튼을 클릭시 해당 액티비티를 종료하는게 아니라
//        메모리상에 있는 프래그먼트를 재사용합니다
//        옵션 설정이 없으면 프래그 먼트 소멸 후 다시 재생성 및 시작을 해서
//        자원 소모가 발생함
        transaction.addToBackStack(null)
        transaction.commit()

    }
}