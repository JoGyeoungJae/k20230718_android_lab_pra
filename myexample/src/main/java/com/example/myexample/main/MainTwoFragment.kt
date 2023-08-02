package com.example.myexample.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myexample.databinding.FragmentMainTwoBinding
import com.example.myexample.list.ItemModel4
import com.example.myexample.list.MyApplication
import com.example.myexample.list.PageListModel
import com.example.myexample.recycler.MyAdapter2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainTwoFragment : Fragment(){
    lateinit var binding: FragmentMainTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val serviceKey =
            "Jo/F8Pswa2Ul50H9F2/iWeQFCrrF2CuVqL+0cEJJVXlLPLQ0TCqZta52lfANIq63d6lc/4VTIeQoIYEFR84pDQ=="
        val resultType = "json"
        binding = FragmentMainTwoBinding.inflate(inflater, container, false)
        /*============================공공 데이터====================================*/
        val applicationContext = requireActivity().applicationContext
        val networkService = (applicationContext as MyApplication).networkService
        val userListCall = networkService.getList(serviceKey, 1, 10, resultType)
        Log.d("lsy", "url:" + userListCall.request().url().toString())

        userListCall.enqueue(object : Callback<PageListModel> {
            override fun onResponse(call: Call<PageListModel>, response: Response<PageListModel>) {

                Log.d("lsy", "실행 여부 확인. userListCall.enqueue")
                val userList = response.body()
                Log.d("lsy", "userList data 값 : ${userList?.getFoodKr?.item}")
                Log.d("lsy", "userList data 갯수 : ${userList?.getFoodKr?.item?.size}")

                val adapter = MyAdapter2(requireContext(), userList?.getFoodKr?.item)

                binding.recyclerView.adapter = adapter

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(
                        requireContext(),
                        LinearLayoutManager.VERTICAL
                    )
                )
            }

            override fun onFailure(call: retrofit2.Call<PageListModel>, t: Throwable) {
                Log.d("lsy", "fail")
                call.cancel()
            }
        })
        /*============================공공 데이터====================================*/


        return binding.root
    }

}
