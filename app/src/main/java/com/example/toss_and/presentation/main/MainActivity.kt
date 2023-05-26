package com.example.toss_and.presentation.main

import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.viewModels
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.example.toss_and.R
import com.example.toss_and.databinding.ActivityMainBinding
import com.example.toss_and.presentation.main.viewmodels.MainViewModel
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

        hideUiNavBar()
        setNavigation()
        binding.bottomNav.selectedItemId = R.id.nav_home

        binding.clTempConsume.doOnLayout {
            getLocation(it)
        }

        registerObserver()
    }

    private fun registerObserver() {
        /* 스크롤 중 특정 위치(소비 카드) 도달 시 clTempConsume이 사라지거나 나타난다 */
        mainVm.consumeFlag.observe(this) {
            if (!it) { // scroll down 하다가 consumeFlag가 T->F 바뀌었다 : 뷰가 사라져야 됨
                binding.clTempConsume.visibility = View.INVISIBLE
                expandBtnv(false)
            }
            else { // scroll up 하다가 consumeFlag가 F->T 바뀌었다 : 뷰가 나타나야 됨
                expandConsume()
                expandBtnv(true)
            }
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

    // Hide the navigation bar and make it immersive
    private fun hideUiNavBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            if (controller != null) {
                controller.hide(WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
        }
    }

    // Get the Y coordinate(unit: pixel) of view(here: clTempConsume) on a screen
    private fun getLocation(view: View) {
        val result = IntArray(2) { 0 }
        val obs = view.viewTreeObserver
        obs.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.getLocationOnScreen(result)
                mainVm.tempConsume = result[1]

                // Don't forget to remove the listener to avoid memory leaks
                obs.removeOnGlobalLayoutListener(this)
            }
        })
    }

    // Horizontal margins of clTempConsume becomes 0 (looks like it expands its width)
    private fun expandConsume() {
        val view = binding.clTempConsume
        view.visibility = View.VISIBLE

        val aniExpand = ValueAnimator.ofInt(16, 0)
        aniExpand.duration = 150
        aniExpand.addUpdateListener { a ->
            val curMargin = a.animatedValue as Int
            val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.leftMargin = curMargin
            layoutParams.rightMargin = curMargin

            view.layoutParams = layoutParams
        }
        aniExpand.start()
    }

    // Radius of btnv's background changes (0->20 or 20->0)
    private fun expandBtnv(b: Boolean) {
        val oldr = if (b) 50f else 0f
        val newr = if (b) 0f else 50f
        val anim = ValueAnimator.ofFloat(oldr, newr)
        anim.duration = 60
        anim.addUpdateListener { a ->
            val curr = a.animatedValue as Float
            with(binding.bottomNav.background as GradientDrawable) {
                cornerRadii = floatArrayOf(
                    curr, curr, // Top-left corner
                    curr, curr, // Top-right corner
                    0f, 0f, // Bottom-left corner (no change)
                    0f, 0f // Bottom-right corner (no change)
                )
            }
        }
        anim.start()
    }
}