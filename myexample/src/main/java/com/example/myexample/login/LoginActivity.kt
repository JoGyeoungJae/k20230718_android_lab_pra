package com.example.myexample.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myexample.databinding.ActivityLoginBinding
import com.example.myexample.db.DatabaseHelper
import com.example.myexample.main.MainActivity


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    var myDB: DatabaseHelper? = null

    var editTextId: EditText? = null
    var editTextPw: EditText? = null
    var buttonLogin: Button? = null
    var buttonback : Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myDB = DatabaseHelper(this)

        editTextId = binding.memid
        editTextPw = binding.mempw
        buttonLogin = binding.login
        buttonback = binding.back
        back()
        LoginData()
    }

    fun back() {
        buttonback!!.setOnClickListener {
            val intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun LoginData() {
        buttonLogin!!.setOnClickListener {
            editTextId = binding.memid
            // res에 조회된 , 테이블의 내용이 들어가 있다. select 의 조회의 결괏값있다.
            val res = myDB!!.allData
            // 결과가 없을 때
            if (res.count == 0) {
                Toast.makeText(this@LoginActivity, "아이디를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
            }
            //결과가 있다면.
            // 자바에서, String 단점, 새로운 문자열이 있다면, 매번 새로 주소를 생성.
            // StringBuffer 하나의 객체에 해당 문자열을 추가만 하는 형태라서, 주소를 새로 생성안함.
            val buffer = StringBuffer()
            //res 형 ->Cursor , 쉽게 엑셀 마치 테이블 , 0행부터 시작한다.
            // res.moveToNext() -> 1행을 의미.
            while (res.moveToNext()) {
                Log.d("joj",res.getString(1))
                //Log.d("joj1",binding.memid.text)
                if(res.getString(1).equals(editTextId)){
                    Toast.makeText(this@LoginActivity,"로그인 성공",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@LoginActivity,"로그인 실패ㅁ",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}