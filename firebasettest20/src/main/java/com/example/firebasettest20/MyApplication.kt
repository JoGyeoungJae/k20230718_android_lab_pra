package com.example.firebasettest20

import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class MyApplication: MultiDexApplication() {
    /*dex 코틀린으로 컴파일 할때 추가된 파일 구조*/
    companion object {//클래스 변수를 사용하겠다, 자바로 치면 sratic 비슷함
        //lateinit 형식으로 선언이 되어있어서 선만만 되었고
        //실제 사용할려면 초기화하는 로직이 반드시 필요함
        //oncreate()에서 초기화시킴
        lateinit var auth: FirebaseAuth
        var email: String? = null
        lateinit var db: FirebaseFirestore
        lateinit var storage: FirebaseStorage
        fun checkAuth(): Boolean {
            /*파이어베이스 인증의 기능을 이용하는 로직
            *  auth.currentUser-> 파이어베이스로 인증된 사용자를 가리킴
            * 로그인 후_> 인증된 유저를 가리킴*/
            var currentUser = auth.currentUser
            return currentUser?.let {
                email = currentUser.email
                currentUser.isEmailVerified
            } ?: let {
                false
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        //실제로 선언된 객체에 할당하는 구조
        auth = Firebase.auth
        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage
    }
}