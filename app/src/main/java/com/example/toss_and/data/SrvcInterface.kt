package com.example.toss_and.data

import com.example.toss_and.data.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface SrvcInterface {
    @GET("asset")
    fun getAssets(
        @Header("Authorization") authorization: String = "1"
    ): Call<ResAssetDto>

    @GET("product/brand/{productId}")
    fun getBrandconDetail(
        @Path("productId") productId: Int,
        @Header("Authorization") authorization: String = "1"
    ): Call<BrandconDto>

    @PATCH("product/brand/{productId}")
    fun clickLike(
        @Header("Authorization") authorization: String = "1",
        @Path("productId") productId: Int
    ): Call<ResLikeDto>

    @POST("product/brand/present/{productId}")
    fun sendGift(
        @Body body: RequestGiftDto,
        @Path("productId") productId: Int,
        @Header("Authorization") authorization: String = "1",
    ): Call<ResponseGiftDto>
}