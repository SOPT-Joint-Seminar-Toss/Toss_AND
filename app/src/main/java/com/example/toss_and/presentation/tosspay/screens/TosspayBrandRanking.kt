package com.example.toss_and.presentation.tosspay.screens

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.toss_and.R
import com.example.toss_and.databinding.TosspayRankingViewBinding

class TosspayBrandRanking(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private var binding: TosspayRankingViewBinding = TosspayRankingViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RankingView)
        val image = typedArray.getResourceId(R.styleable.RankingView_image, R.drawable.img_baemin3x)
        val title = typedArray.getString(R.styleable.RankingView_title)
        val rank = typedArray.getString(R.styleable.RankingView_rank)

        binding.ivRank.setImageResource(image)
        binding.tvBrandName.text = title.toString()
        binding.tvRank.text = rank
    }
}