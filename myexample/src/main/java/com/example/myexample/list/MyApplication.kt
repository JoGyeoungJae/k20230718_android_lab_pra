package com.example.myexample.list

import android.app.Application
import com.example.ch18_net_test1.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application(){

    //add....................................
    var networkService: NetworkService

    val retrofit: Retrofit
        get() = Retrofit.Builder()
                //도보여행, 부산맛집정보서비스
            .baseUrl("https://apis.data.go.kr/6260000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        networkService = retrofit.create(NetworkService::class.java)
    }
}