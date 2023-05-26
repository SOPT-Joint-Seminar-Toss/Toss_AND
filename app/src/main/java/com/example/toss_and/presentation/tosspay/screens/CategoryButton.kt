package com.example.toss_and.presentation.tosspay.screens

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.toss_and.R
import com.example.toss_and.databinding.TosspayCategoryButtonBinding

class CategoryButton(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {
    private var binding: TosspayCategoryButtonBinding =
        TosspayCategoryButtonBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CategoryButton)

        val image =
            typedArray.getResourceId(R.styleable.CategoryButton_image, R.drawable.img_cafe3x)
        binding.ivCategory.setImageResource(image)

        val title = typedArray.getString(R.styleable.CategoryButton_title)
        binding.tvCategory.text = title

        // 데이터를 캐싱해두어 가비지컬렉션에서 제외시키도록 하는 함수
        // typedArray 사용 후 호출해야하나, 커스텀뷰가 반복 사용되는 것이 아니라면 호출하지 않아도 무방하다.
        typedArray.recycle()
    }
}