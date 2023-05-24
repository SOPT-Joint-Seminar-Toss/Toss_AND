package com.example.toss_and.data

import com.example.toss_and.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiFactory {
    private const val SOPT_URL = BuildConfig.SOPT_BASE_URL

    val httpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    val okhttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(SOPT_URL)
            .client(okhttpClient)
            .addConverterFactory(Json.asConverterFactory(("application/json".toMediaType())))
            .build()
    }

    inline fun <reified T> createSopt(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val service = ApiFactory.createSopt<SrvcInterface>()
}