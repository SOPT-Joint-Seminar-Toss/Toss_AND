package com.example.toss_and.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.toss_and.R
import com.example.toss_and.databinding.ActivityMainBinding
import com.example.toss_and.util.base.BindingActivity

class MainActivity: BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainVm by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setViewPager()
        setNavigation()

        /* TODO: activity에서 쓴다면 이런 식으로 쓰면 되겠찌 >< */
        mainVm.getAssets()
        mainVm.assetResult.observe(this) {
            Log.d("ABC", it.toString())
        }
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