package com.example.test10_11_12joj.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test10_11_12joj.databinding.ActivityViewPager2FragmentBinding
import com.example.test10_11_12joj.fragment.OneFragment
import com.example.test10_11_12joj.fragment.ThreeFragment
import com.example.test10_11_12joj.fragment.TwoFragment

class ViewPager2_Fragment : AppCompatActivity() {
    lateinit var binding: ActivityViewPager2FragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPager2FragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*뷰페이저 2 구현 - 프레그먼트 방식으로 연결 */
        val datas = mutableListOf<String>()
        for(i in 1..3){
            datas.add("Item $i")
        }
        val adapter= MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
    }
    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init {
            fragments= listOf(OneFragment(), TwoFragment(), ThreeFragment())
            Log.d("kkang" ,"fragments size : ${fragments.size}")
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}