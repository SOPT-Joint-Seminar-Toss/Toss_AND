package com.example.toss_and.presentation.main

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.toss_and.R
import com.example.toss_and.databinding.ActivityMainBinding
import com.example.toss_and.util.base.BindingActivity

class MainActivity: BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setViewPager()
        setNavigation()
    }

    private fun setViewPager() {
        binding.viewPager.adapter = MainPagerAdapter(this)
        // 슬라이드해서 페이지가 변경되면 bottom nav의 탭도 그 페이지로 활성화
        binding.viewPager.registerOnPageChangeCallback(
            object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomNav.menu.getItem(position).isChecked = true
                }
            }
        )
    }

    private fun setNavigation() {
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    binding.viewPager.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.nav_benefit -> {
                    binding.viewPager.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                R.id.nav_tosspay -> {
                    binding.viewPager.currentItem = 2
                    return@setOnItemSelectedListener true
                }
                R.id.nav_stock -> {
                    binding.viewPager.currentItem = 3
                    return@setOnItemSelectedListener true
                }
                R.id.nav_all -> {
                    binding.viewPager.currentItem = 4
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }
}