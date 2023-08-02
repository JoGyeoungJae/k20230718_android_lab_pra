package com.example.myexample.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myexample.databinding.FragmentMainThreeBinding

class MainThreeFragment : Fragment() {
    lateinit var binding : FragmentMainThreeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

}