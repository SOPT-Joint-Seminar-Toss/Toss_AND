package com.example.toss_and.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrandconDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: Data
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Int,
        @SerialName("brandTitle")
        val brandTitle: String,
        @SerialName("imageUrl")
        val imageUrl: String,
        @SerialName("productTitle")
        val productTitle: String,
        @SerialName("price")
        val price: Int,
        @SerialName("point")
        val point: Int,
        @SerialName("like")
        val isLike: Boolean,
        @SerialName("expiration")
        val expiration: Int,
        @SerialName("productInfo")
        val productInfo: String
    )
}
