package com.example.toss_and.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.toss_and.R
import com.example.toss_and.databinding.ActivityMainBinding
import com.example.toss_and.presentation.MainViewModel
import com.example.toss_and.presentation.TempPayFragment
import com.example.toss_and.presentation.main.all.AllFragment
import com.example.toss_and.presentation.main.benefit.BenefitFragment
import com.example.toss_and.presentation.main.home.HomeFragment
import com.example.toss_and.presentation.main.stock.StockFragment
import com.example.toss_and.util.base.BindingActivity


class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainVm by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setNavigation()
        binding.bottomNav.selectedItemId = R.id.nav_home

        /* TODO: activity에서 쓴다면 이런 식으로 쓰면 되겠찌 >< */
        mainVm.getAssets()
        mainVm.assetResult.observe(this) {
            Log.d("ABC", it.toString())
        }
    }

    private fun setNavigation() {
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.nav_benefit -> {
                    replaceFragment(BenefitFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.nav_tosspay -> {
                    replaceFragment(TempPayFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.nav_stock -> {
                    replaceFragment(StockFragment())
                    return@setOnItemSelectedListener true
                }

                R.id.nav_all -> {
                    replaceFragment(AllFragment())
                    return@setOnItemSelectedListener true
                }

                else -> return@setOnItemSelectedListener false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}