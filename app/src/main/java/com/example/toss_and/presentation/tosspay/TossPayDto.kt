package com.example.toss_and.presentation.tosspay

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseGroupBuyingDto(
    @SerialName("code")
    val code: Int?,
    @SerialName("message")
    val message: String?,
    @SerialName("data")
    val data: List<Data>?
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Int?,
        @SerialName("imageUrl")
        val imageUrl: String?,
        @SerialName("title")
        val title: String?,
        @SerialName("discountRate")
        val discountRate: Int?,
        @SerialName("price")
        val price: Int?,
        @SerialName("endDate")
        val endDate: String?
    )
}

@Serializable
data class ResponseBrandconDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<Data>?
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Int,
        @SerialName("imageUrl")
        val imageUrl: String,
        @SerialName("productTitle")
        val productTitle: String,
        @SerialName("brandTitle")
        val brandTitle: String,
        @SerialName("price")
        val price: Int,
        @SerialName("point")
        val point: Int
    )
}