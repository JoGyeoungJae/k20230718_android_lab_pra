package com.example.test10_11_12joj.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.example.test10_11_12joj.R
import com.example.test10_11_12joj.databinding.ActivityJetBinding

class JetActivity : AppCompatActivity() {
    lateinit var binding : ActivityJetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        //툴바를 직접 만들어 뷰에 붙이는 작업
        //뷰, 뒷단 코드 가져오기


        //액션바(테마 사용)-> 툴바로 대체 할 예정 기초
        //메뉴 구성방법 1코드로 2 xml구성하는 방식(형재 xml 구성)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 키보드의 검색 버튼을 클릭한 순간의 이벤트
                return true
            }
        })
        return true
    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.menu, menu)
//
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
//        R.id.menu11 -> {
//            Log.d("kkang", "menu1 click")
//            Toast.makeText(this@JetActivity,"0클릭",Toast.LENGTH_SHORT).show()
//            true
//        }
//        R.id.menu22 -> {
//            Log.d("kkang", "menu2 click")
//            Toast.makeText(this@JetActivity,"1클릭",Toast.LENGTH_SHORT).show()
//            true
//        }
//        R.id.menu33 -> {
//            Log.d("kkang", "menu3 click")
//            Toast.makeText(this@JetActivity,"2클릭",Toast.LENGTH_SHORT).show()
//            true
//        }
//        R.id.menu44 -> {
//            Log.d("kkang", "menu4 click")
//            Toast.makeText(this@JetActivity,"3클릭",Toast.LENGTH_SHORT).show()
//            true
//        }
//        R.id.menu55 -> {
//            Log.d("kkang", "menu5 click")
//            Toast.makeText(this@JetActivity,"4클릭",Toast.LENGTH_SHORT).show()
//            true
//        }
//
//        else -> super.onOptionsItemSelected(item)
//    }
}