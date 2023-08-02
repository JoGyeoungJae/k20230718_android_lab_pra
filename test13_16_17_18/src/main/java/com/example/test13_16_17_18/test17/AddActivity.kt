package com.example.test13_16_17_18.test17

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //액션바 메뉴 아이템 클릭 리스터 부분에 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.menu_add_save ->{
            //add........................
            //입력창에 입력된 값 가져오기
            val inputData = binding.addEditView.text.toString()
            //DBHelper: 일반 클래스, 매개변수로 현재 액티비티 this로 사용
            //writableDatabase 문자열을 이용해서 insert 작업 예정
            val db = DBHelper(this).writableDatabase
            //기본함수 execSQL 함수를 이용해서, insert를 진행
            //db.execSQL(insert 문법, 동적 매개변수 부분의 값으로 할당 될 값을 배열의 요소로 제공)
            db.execSQL(
                "insert into TODO_TB (todo) values (?)",
                arrayOf<String>(inputData)
            )
            db.close()
            //후처리 부분
            //메인 플로팅 액션 버튼 클릭 -> 현재 add 입력창에와서 텍스트 입력후
            //입력된 데이터를 인텐트로 다시 돌려 보냄 ,데이터 세터
            val intent = intent
            intent.putExtra("result", inputData)
            setResult(Activity.RESULT_OK, intent)
            //액티비티 종료
            finish()
            true
        }
        else -> true
    }
}