package com.example.toss_and.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.toss_and.R
import com.example.toss_and.databinding.ActivityMainBinding
import com.example.toss_and.util.base.BindingActivity

class MainActivity: BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setNavigation()
    }

    private fun setNavigation() {
        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    return@setOnItemSelectedListener true
                }
                R.id.nav_benefit -> {
                    return@setOnItemSelectedListener true
                }
                R.id.nav_tosspay -> {
                    return@setOnItemSelectedListener true
                }
                R.id.nav_stock -> {
                    return@setOnItemSelectedListener true
                }
                R.id.nav_all -> {
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }
        }
    }
}