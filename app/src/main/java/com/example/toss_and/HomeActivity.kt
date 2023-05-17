package com.example.toss_and

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toss_and.databinding.ActivityHomeBinding
import com.example.toss_and.presentation.util.base.BindingActivity

class HomeActivity: BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}