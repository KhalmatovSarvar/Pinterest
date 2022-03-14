package com.example.pinterest.activity.database.Retrofit

import com.example.pinterest.activity.database.Retrofit.service.PhotoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    private var BASE_URL = "https://api.unsplash.com/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val apiService:PhotoService = retrofit.create(PhotoService::class.java)
}