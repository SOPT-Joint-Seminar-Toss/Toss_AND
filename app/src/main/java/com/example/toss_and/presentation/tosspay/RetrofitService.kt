package com.example.toss_and.presentation.tosspay

import retrofit2.Call
import retrofit2.http.GET

interface TossPayService {
    @GET("product")
    fun getProduct(
    ): Call<ResponseGroupBuyingDto>

    @GET("product/brand")
    fun getBrand(
    ): Call<ResponseBrandconDto>
}