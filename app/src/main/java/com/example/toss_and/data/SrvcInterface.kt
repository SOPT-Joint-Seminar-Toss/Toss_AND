package com.example.toss_and.data

import com.example.toss_and.data.model.ResAssetDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface SrvcInterface {
    @GET("asset")
    fun getAssets(
        @Header("Authorization") authorization: String = "1"
    ): Call<ResAssetDto>
}