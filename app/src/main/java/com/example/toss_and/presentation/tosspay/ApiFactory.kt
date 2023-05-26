package com.example.toss_and.presentation.tosspay

import com.example.toss_and.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiFactory {
    private const val SOPT_BASE_URL = BuildConfig.SOPT_BASE_URL
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(SOPT_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val tossPayService = ApiFactory.create<TossPayService>()
}