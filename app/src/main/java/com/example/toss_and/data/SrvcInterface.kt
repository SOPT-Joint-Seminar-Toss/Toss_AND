package com.example.toss_and.data

import com.example.toss_and.data.model.BrandconDto
import com.example.toss_and.data.model.ResAssetDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface SrvcInterface {
    @GET("asset")
    fun getAssets(
        @Header("Authorization") authorization: String = "1"
    ): Call<ResAssetDto>

    // TODO: 여러분의 API를 추가하세용~

    @GET("product/brand/{productId}")
    fun getBrandconDetail(
        @Path("productId") productId: Int,
        @Header("Authorization") authorization: String = "1"
    ): Call<BrandconDto>
}