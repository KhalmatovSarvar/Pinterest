package com.example.pinterest.activity.database.Retrofit.service

import com.example.pinterest.activity.models.PhotoHome
import com.example.pinterest.activity.models.PhotoItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {

//    companion object {
//        private const val accessKey = "rMjW0cEyNvjBt40xzDHHgO7ZDk_A5vvbmNpMcYJB9d4"  //second key
//        private const val clientId = "Client-ID"
//    }


    companion object{
//        const val access_key: String = "-0_5ozJAEGQD92NkSnugRiPo1clxljtAqmJKQxFrwN4"
        const val access_key: String = "rMjW0cEyNvjBt40xzDHHgO7ZDk_A5vvbmNpMcYJB9d4"
    }

    @Headers("Authorization:Client-ID $access_key")
    @GET("photos")
    fun getPhotos(@Query("page") page: Int, @Query("per_page") per_page: Int) : Call<ArrayList<PhotoHome>>

    @Headers("Authorization:Client-ID $access_key")
    @GET("photos/{id}/related")
    fun getRelatedPhotos(@Path("id") id: String): Call<PhotoItem>  // get similar photos

}