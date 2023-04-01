package com.faithdeveloper.harry.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

//    base url

    private const val BASE_URL = "https://hp-api.onrender.com/api/"

    private val okHttp = OkHttpClient.Builder()

    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttp.build())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}