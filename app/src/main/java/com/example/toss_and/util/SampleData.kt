package com.example.toss_and.util

import com.example.toss_and.R
import com.example.toss_and.presentation.main.home.HomeBtmCardsDto

object SampleData {
    val homeBtmCards: List<HomeBtmCardsDto> = listOf(
        HomeBtmCardsDto("돈 버는 법", "매일 포인트\n받기", R.drawable.icn_point),
        HomeBtmCardsDto("최근", "오늘의\n머니 팁", R.drawable.icn_lamp),
        HomeBtmCardsDto("자주", "신용점수\n보기", R.drawable.icn_moneybag),
    )
}