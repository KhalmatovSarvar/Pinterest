package com.example.pinterest.activity.database.Retrofit.service

import com.example.pinterest.activity.models.PhotoHome
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {

    companion object {
//        private const val accessKey = "rMmekWEBJ3TwOIbrkmasVBfMYodsiwNQfys5AzgFxPQ"  first key
        private const val accessKey = "cTeO4aeuL-DPuK8UkF8z8JWebiXAVBg7tMmfrX5kftc"
        private const val clientId = "Client-ID"
    }

    @Headers("Authorization:$clientId $accessKey")

    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<ArrayList<PhotoHome>>

    @GET("photos/{id}")
    fun getPhotoById(@Path("id") id: Int): Call<PhotoHome>
}