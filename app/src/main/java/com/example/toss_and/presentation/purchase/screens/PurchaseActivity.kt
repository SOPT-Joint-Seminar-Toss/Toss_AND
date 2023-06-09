package com.example.toss_and.presentation.purchase.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.toss_and.R
import com.example.toss_and.databinding.ActivityPurchaseBinding
import com.example.toss_and.presentation.gift.screens.GiftActivity
import com.example.toss_and.presentation.purchase.adapters.TablayoutViewPagerAdapter
import com.example.toss_and.presentation.purchase.viewmodels.PurchaseViewModel
import com.example.toss_and.util.base.BindingActivity
import com.example.toss_and.util.showToast
import com.google.android.material.tabs.TabLayoutMediator
import java.text.DecimalFormat

class PurchaseActivity : BindingActivity<ActivityPurchaseBinding>(R.layout.activity_purchase) {
    private lateinit var tablayoutViewPagerAdapter: TablayoutViewPagerAdapter
    private val purchaseVm by viewModels<PurchaseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        goBack()
        initAdapter()
        initTabLayout()
        goGiftActivity()

        registerClickEvent()
        registerObserver()
    }

    private fun goBack() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun registerObserver() {
        purchaseVm.getBrandconDetail(1)
        purchaseVm.brandconResult.observe(this) {
            Log.d("PurchaseActivity", it.toString())
            with(binding) {
                Glide.with(applicationContext)
                    .load(it.data.imageUrl)
                    .into(ivProduct)
                tvBrand.text = it.data.brandTitle
                tvItemName.text = it.data.productTitle
                val priceFormatter = DecimalFormat("###,###")
                val formattedStringPrice: String = priceFormatter.format(it.data.price)
                tvItemPrice.text = formattedStringPrice+"원"
                tvPoint.text = it.data.point.toString()+"원"
                tvValidityDate.text = it.data.expiration.toString()+"일"
                if (it.data.like) {
                    btnHeart.setImageResource(R.drawable.icn_heart_active)
                } else {
                    btnHeart.setImageResource(R.drawable.icn_heart_inactive)
                }
            }
        }

        purchaseVm.likeResult.observe(this) {
            if (it) {
                showToast( "이 상품을 찜했습니다!")
            }
        }
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

    private fun goGiftActivity() {
        binding.btnGift.setOnClickListener {
            val intent = Intent(this, GiftActivity::class.java)
            startActivity(intent)
        }
    }
}