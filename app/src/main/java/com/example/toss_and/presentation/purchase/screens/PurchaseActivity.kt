package com.example.toss_and.presentation.purchase.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.toss_and.databinding.ActivityPurchaseBinding
import com.example.toss_and.presentation.purchase.adapters.TablayoutViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class PurchaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPurchaseBinding
    private lateinit var tablayoutViewPagerAdapter: TablayoutViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initTabLayout()
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
}