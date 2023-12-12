package com.minthanhtike.retrofitjetpack.api

import com.minthanhtike.retrofitjetpack.data.PhotoModels
import com.minthanhtike.retrofitjetpack.data.PhotoModelsItem
import retrofit2.http.GET

interface ApiRepo {
    @GET("/albums/1/photos")
    suspend fun getAllPhotos():PhotoModels
}