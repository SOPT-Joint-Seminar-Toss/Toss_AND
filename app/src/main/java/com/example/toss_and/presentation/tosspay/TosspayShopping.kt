package com.example.toss_and.presentation.tosspay

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.toss_and.R
import com.example.toss_and.databinding.TosspayShoppingViewBinding

class TosspayShopping(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private var binding: TosspayShoppingViewBinding = TosspayShoppingViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShoppingView)

        val image =
            typedArray.getResourceId(R.styleable.ShoppingView_image, R.drawable.img_chicken3x)
        val title = typedArray.getString(R.styleable.ShoppingView_title)
        val detail = typedArray.getString(R.styleable.ShoppingView_detail)

        binding.ivShopping.setImageResource(image)
        binding.tvShopping.text = title
        binding.tvShoppingDetail.text = detail

    }
}