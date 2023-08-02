package com.example.test13_16_17_18.test18reqres

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test13_16_17_18.databinding.ActivityMain9Binding
import com.example.test13_16_17_18.test18reqres.Model.UserListModel
import com.example.test13_16_17_18.test18reqres.adpater.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity3 : AppCompatActivity() {
    lateinit var binding: ActivityMain9Binding
    // 리사이클러뷰 작업.
    // 참고 코드, 원본 소스 코드, test18 -> retrofit2 -> MyAdapter.kt, Retrofit 참고.
    // 뷰도 같이 복사했음. 1) item_retrofit.xml 2) activity_retrofit.xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain9Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //applicationContext 시스템에 등록된 설정된 파일
        //위에 변수를 MyApplication 형변환
        //MyApplication ->INetworkService
        val networkService = (applicationContext as MyApplication).networkService

        //공공데이터 파라미터 변경 부분.
        //http://apis.data.go.kr/6260000/FoodService/getFoodKr?resultType=json&serviceKey=ALRX9GpugtvHxcIO%2FiPg1vXIQKi0E6Kk1ns4imt8BLTgdvSlH%2FAKv%2BA1GcGUQgzuzqM3Uv1ZGgpG5erOTDcYRQ%3D%3D&numOfRows=10&pageNo=1
        val userListCall = networkService.doGetUserList(
            "json",)
        Log.d("lsy", "url:" + userListCall.request().url().toString())
        //enqueue 실제 네트워크 통신의 시작 부분
        userListCall.enqueue(object : Callback<UserListModel> {
            override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
                //response 응답 객체에 우리가 우너하는 데이터의 갹체(배열)이 넘어온다.
                val userList = response.body()
                Log.d("lsy","Test18 userList data 값 : ${userList?.data}")
                //.......................................

                binding.recyclerView.adapter= MyAdapter(this@MainActivity3, userList?.data)
                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity3, LinearLayoutManager.VERTICAL)
                )

                binding.pageView.text=userList?.page
                binding.totalView.text=userList?.total
            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                call.cancel()
            }
        })

        binding.testButton.setOnClickListener {
//            val call: Call<UserModel> = networkService.test1()//https://reqres.in/users/list?sort=desc

//            val call: Call<UserModel> = networkService.test2("10", "lsy")//https://reqres.in/group/10/users/lsy

//            val call: Call<UserModel> = networkService.test3("age", "lsy")//https://reqres.in/group/users?sort=age&name=lsy

//            val call: Call<UserModel> = networkService.test4(
//                mapOf<String, String>("one" to "hello", "two" to "world"),
//                "lsy"
//            )//https://reqres.in/group/users?one=hello&two=world&name=lsy

//            val call: Call<UserModel> = networkService.test5(
//                UserModel(id="1", firstName = "gildong", lastName = "hong", avatar = "some url"),
//                "lsy"
//            )//https://reqres.in/group/users?name=lsy

//            val call: Call<UserModel> = networkService.test6(
//                "gildong 길동",
//                "hong 홍",
//                "lsy"
//            )//https://reqres.in/user/edit?name=lsy

//            val list: MutableList<String> = ArrayList()
//            list.add("홍길동")
//            list.add("류현진")
//            val call = networkService.test7(list)

//            val call = networkService.test9("http://www.google.com", "lsy")//http://www.google.com/?name=lsy
//
//            Log.d("lsy","url:"+call.request().url().toString())

        }

    }
}