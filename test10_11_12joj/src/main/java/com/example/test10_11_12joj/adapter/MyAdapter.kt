package com.example.test10_11_12joj.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_11_12joj.databinding.Item342Binding

//어댐터를 따로 분리해서 반들고
//구성품은 뷰 홀더 클래스를 같이 구성하는 편
//목록의 아이템 역할을 하는 뷰를 직접 만들어야 함
//xml 레이아웃, -> 개별로 rex -> layout 직지버 만들기

//뷰-> 자동으로 뷰 바인딩이 동작해서 item42binding 파일을 메모리 생성

//뷰 홀더 매캐변수 :목록의 구성요소(아이템)
class MyViewHolder(val binding: Item342Binding): RecyclerView.ViewHolder(binding.root)
//어댑터 매개변수 : 현재 임시 데이터(액티비티에 현재 있고) 원래 네트워크 라이브러리 사용
//데이터 가져올 예정
class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    //목록의 요소를 자동으로 갯수를 파악해서 출력해주는 역할
    override fun getItemCount(): Int{
        Log.d("kkang", "init datas size: ${datas.size}")
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(Item342Binding.inflate(LayoutInflater.from(parent.context), parent, false))
//데이터와 뷰를 연결하는 역할
    //데이터가 변경이 될때마다 여기 함수가 자동으로 호출
    //받아온 데이터를 해장 뷰에 연결해서 보여주는 역할
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("kkang","onBindViewHolder : $position")
        val binding=(holder as MyViewHolder).binding
        //뷰에 데이터 출력
        binding.itemData.text= datas[position]

        //뷰에 이벤트 추가
        binding.itemRoot.setOnClickListener{
            Log.d("kkang", "item root click : $position")
        }
    //목록에 데이터를 임의로 받아오는 부분이 아니라 기존 데이터에 추가 수정할때
    //반영이 안되는 경우 사용하는 함수
    //notifyDataSetChanged()
    }
}