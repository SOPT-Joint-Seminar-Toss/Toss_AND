package com.example.toss_and.presentation

import android.os.Bundle
import com.example.toss_and.R
import com.example.toss_and.databinding.ActivityHomeBinding
import com.example.toss_and.util.base.BindingActivity

class HomeActivity: BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.cl_fragment_container, HomeFragment())
            .commit()
    }
}