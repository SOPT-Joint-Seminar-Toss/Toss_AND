package com.example.toss_and.presentation.purchase.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.toss_and.R
import com.example.toss_and.databinding.ActivityPurchaseBinding
import com.example.toss_and.presentation.purchase.PurchaseViewModel
import com.example.toss_and.presentation.purchase.adapters.TablayoutViewPagerAdapter
import com.example.toss_and.util.base.BindingActivity
import com.google.android.material.tabs.TabLayoutMediator

class PurchaseActivity : BindingActivity<ActivityPurchaseBinding>(R.layout.activity_purchase) {
    private lateinit var tablayoutViewPagerAdapter: TablayoutViewPagerAdapter
    private val purchaseVm by viewModels<PurchaseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        initAdapter()
        initTabLayout()

        registerClickEvent()
        registerObserver()
    }

    private fun initAdapter() {
        val fragmentList = listOf(ProductInfoFragment(), ReviewFragment())

        tablayoutViewPagerAdapter = TablayoutViewPagerAdapter(supportFragmentManager, lifecycle)
        tablayoutViewPagerAdapter.fragments.addAll(fragmentList)

        binding.vpViewpager.adapter = tablayoutViewPagerAdapter
    }

    private fun initTabLayout() {
        val tabLabel = listOf("상품정보", "후기")
        TabLayoutMediator(binding.tlTablayout, binding.vpViewpager) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }

    private fun registerClickEvent() {
        binding.btnHeart.setOnClickListener {
            with(binding.btnHeart) {
                if (isClickable) {
                    this.setImageResource(R.drawable.icn_heart_active)
                    purchaseVm.sendLike(1)
                    this.isClickable = false
                }
            }
        }
    }

    private fun registerObserver() {
        purchaseVm.likeResult.observe(this) {
            if (it) {
                Toast.makeText(this, "이 상품을 찜했습니다!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}