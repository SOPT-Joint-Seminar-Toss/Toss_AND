package com.example.toss_and.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

// ViewPager에서 이용하는 페이지 뷰를 생성하기 위한 어댑터
class MainPagerAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> BenefitFragment()
            2 -> PayFragment() // TODO: 추후 수정 예정
            3-> StockFragment()
            4 -> AllFragment()
            else -> HomeFragment()
        }
    }
}