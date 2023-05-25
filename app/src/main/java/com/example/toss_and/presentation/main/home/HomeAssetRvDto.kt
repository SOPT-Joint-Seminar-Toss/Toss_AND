package com.example.toss_and.presentation.main.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class HomeAssetRvDto (
    val id: Int,
    val img: Int,
    val title: String,
    val content: String,
    val btn: Boolean
)