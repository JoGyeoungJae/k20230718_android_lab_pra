package com.example.k20230718_android_lab_pra

import java.util.Scanner

class User(val name :String,val id :String,val pw:String,val email:String,val phone:String) {

}
/*class LoginTest{
    companion object{
        fun login(user :User){
            if(user.id == insertId && user.pw == insertPw){
                println("로그인 성공")
            }
        }
    }
}*/
    val sc =  Scanner(System.`in`)
fun main() {
    print("이름:")
    val name :String = sc.next()
    print("ID:")
    val id :String =  sc.next()
    print("PW:")
    val pw :String =  sc.next()
    print("email:")
    val email :String =  sc.next()
    print("phone:")
    val phone :String =  sc.next()
    val user = User(name,id,pw,email,phone)
    var flag = true
    while (true){
        println("로그인")
        print("아이디 : ")
        val insertId = sc.next()
        print("비밀번호 :")
        val insertPw = sc.next()
        if(user.id == insertId && user.pw == insertPw){
            println("로그인 성공")
            println("=========================")
            flag = false
        }else{
            println("로그인 실패")
            println("=========================")
        }
        if(!flag){
            break
        }
    }

}