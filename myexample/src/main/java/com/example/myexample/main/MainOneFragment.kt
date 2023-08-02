package com.example.myexample.main

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myexample.databinding.FragmentMainOneBinding
import com.example.myexample.list.MyApplication
import com.example.myexample.list.PageListModel
import com.example.myexample.recycler.MyAdapter2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainOneFragment : Fragment() {
    lateinit var binding : FragmentMainOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainOneBinding.inflate(inflater, container, false)

        return binding.root
    }

}