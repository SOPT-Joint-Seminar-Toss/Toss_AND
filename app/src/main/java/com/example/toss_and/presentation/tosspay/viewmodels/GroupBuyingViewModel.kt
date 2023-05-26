package com.example.toss_and.presentation.tosspay.viewmodels

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import com.example.toss_and.R

data class RecycleGroupBuyingData(
    @DrawableRes // 안드로이드의 Meta Annotation입니다.
    val imgId: Int,
    val people: String
)

class GroupBuyingViewModel : ViewModel() {
    val mockProductList = listOf<RecycleGroupBuyingData>(
        RecycleGroupBuyingData(
            imgId = R.drawable.img_profiles1,
            people = "9,536명이 구매중"
        ),
        RecycleGroupBuyingData(
            imgId = R.drawable.img_profiles2,
            people = "38,976명이 구매중"
        )
    )
}