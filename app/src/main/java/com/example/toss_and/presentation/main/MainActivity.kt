package com.example.toss_and.presentation.main

import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.core.view.doOnLayout
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

        hideUiNavBar()
        setNavigation()
        binding.bottomNav.selectedItemId = R.id.nav_home

        /* TODO: activity에서 쓴다면 이런 식으로 쓰면 되겠찌 >< */
        mainVm.getAssets()
        mainVm.assetResult.observe(this) {
            Log.d("ABC", it.toString())
        }

        /* TODO: 해보는 중 */
        mainVm.consumeFlag.observe(this) {
            Log.e("ABC", "consumeFlag is: $it")
            if (!it) { // scroll down 하다가 consumeFlag가 T->F 바뀌었다 : 뷰가 사라져야 됨
                Log.e("ABC", "consumeFlag became FALSE!")
                binding.clTempConsume.visibility = View.INVISIBLE
            }
            else { // scroll up 하다가 consumeFlag가 F->T 바뀌었다 : 뷰가 나타나야 됨
                Log.e("ABC", "consumeFlag became TRUE!")
                binding.clTempConsume.visibility = View.VISIBLE
                val anim = ValueAnimator.ofInt(16, 0)
                anim.addUpdateListener { valAnimator ->
                    val layoutParams = binding.clTempConsume.layoutParams as FrameLayout.LayoutParams
                    layoutParams.marginStart = valAnimator.animatedValue as Int
                    layoutParams.marginEnd = valAnimator.animatedValue as Int
                    binding.clTempConsume.layoutParams = layoutParams
                }
                anim.duration = 200
                anim.start()
            }
        }

        // TODO: 해보는 중
        binding.clTempConsume.doOnLayout {
            val result = IntArray(2) { 0 }
            val obs = binding.clTempConsume.viewTreeObserver
            obs.addOnGlobalLayoutListener(object: ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    binding.clTempConsume.getLocationOnScreen(result)

                    Log.e("ABC", "mainVm.tempConsume is ${result[1]}")
                    mainVm.tempConsume = result[1]

                    obs.removeOnGlobalLayoutListener(this)
                }
            })
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
}