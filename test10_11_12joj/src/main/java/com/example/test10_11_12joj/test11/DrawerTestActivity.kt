package com.example.test10_11_12joj.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.test10_11_12joj.R
import com.example.test10_11_12joj.databinding.ActivityDrawerTestBinding

class DrawerTestActivity : AppCompatActivity() {
    lateinit var binding:ActivityDrawerTestBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //ActionBarDrawerToggle 버튼 적용

        /*뷰 부분을 잘 보셔야 합니다
        * 드로우워태그 하위에 뷰 2개가 있고
        * 첫번째는 메인화면
        * 두번째는 사이드 메뉴
        *
        * R.string.drawer_opened, R.string.drawer_closed-> values/strings.xml 설정
        * */


        //ActionBarDrawerToggle 버튼 적용
        toggle = ActionBarDrawerToggle(this, binding.drawer,
            R.string.drawer_opened,
            R.string.drawer_closed
        )
        //액션바 토글 부분 연결시켜서 버튼 클릭하면 서랍화면이 보인다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}