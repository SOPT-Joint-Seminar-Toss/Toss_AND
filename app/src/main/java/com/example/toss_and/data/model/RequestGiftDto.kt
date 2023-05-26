package com.example.toss_and.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestGiftDto(
    @SerialName("cardType")
    val cardType: String,
    @SerialName("content")
    val content: String
)