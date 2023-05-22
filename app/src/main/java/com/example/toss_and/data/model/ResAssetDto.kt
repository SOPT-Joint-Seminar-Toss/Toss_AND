package com.example.toss_and.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResAssetDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val `data`: List<Data>
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Int,
        @SerialName("title")
        val title: String,
        @SerialName("balance")
        val balance: Int
    )
}