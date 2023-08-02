package com.example.test13_16_17_18.test17

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test13_16_17_18.R
import com.example.test13_16_17_18.databinding.ActivityMain4Binding
import com.example.test13_16_17_18.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMain4Binding
    //AddActivity에서 입력된 한줄의 텍스트들을 요소로 리스트에 보관
    var datas: MutableList<String>? = null
    //입력된 문자열 내용을 ,리사이클러뷰로 출력
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //입력 창으로 텍스트 입력후, 저장 버튼을 누르면 여기로 돌아옴
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            //돌아온 결과값은 it 라는 객체 Map 형태로 저장
            //키: result 라는 부분의 값을 가지고 와서
            //datas 라는 리스트에 add 답기
            //어댑터 객체의 함수중에 변경 사항을 알리는 함수를 수행
            it.data!!.getStringExtra("result")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
        }

        //플로팅 액션바 버튼, 클릭 이벤트 처리 -> 입력 액티비티로 이동
        //후처리를 하는 함수,requestLauncher
        //입력 창에서 to do로 입력후, 입력된 값을 가지도 되돌아온다
        //  val requestLauncher = registerForActivityResult(이부분으로 돌가감

        //AddActivity 데이터 처리후 setter 부분이 있음
        binding.mainFab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }
        //변경가능한 리스트 형식으로 객체 선언
        datas= mutableListOf<String>()

        //조회......................
        /*readableDatabase ->읽기*/
        val db = DBHelper(this).readableDatabase
        /*cursor - 조회된 결과를 테이블 현식으로 저장된 객체*/
        val cursor = db.rawQuery("select * from TODO_TB", null)
        /*테이블 형식으로 저장되어있음*/
        cursor.run {
            /*반복문으로 커서 테이블에 데이터를 한행씩 불러와서, 해당 컬럼을 가져온다*/
            while(moveToNext()){
                datas?.add(cursor.getString(1))
            }
        }
        /*디비 서버에서 조회된 내용을 현재 메모리 datas라는 리스트에 담기
        * 디비 사용 반납*/
        db.close()
        //리사이클러뷰 적용
        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager=layoutManager
        //디비 서버에서 받아온 데이터를 메모리상의 임의 객체 datas 담아서
        //어댑터 클래스에 연결
        adapter=MyAdapter(datas)
        //어댑터 클레스에 적용된 데이터 <->뷰 결과를 뷰에 적용하는 부분
        binding.mainRecyclerView.adapter=adapter

        binding.mainRecyclerView.addItemDecoration(

            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId===R.id.menu_main_setting){
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}