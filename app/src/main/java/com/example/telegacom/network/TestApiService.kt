package com.example.telegacom.network

import android.provider.MediaStore.Video
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://checkiant.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TestApiService {
    @GET("api_v1b/get-channels")
    suspend fun getProperties() : NetworkChannelContainer
}

object TestApi {
    val retrofitService : TestApiService by lazy { retrofit.create(TestApiService::class.java) }
}