package com.minthanhtike.retrofitjetpack.api

import com.minthanhtike.retrofitjetpack.data.PhotoModels
import com.minthanhtike.retrofitjetpack.data.PhotoModelsItem
import javax.inject.Inject

class ApiService @Inject constructor(
    private val apiRepo: ApiRepo
) {
    suspend fun getAllPhotos(): List<PhotoModelsItem> {
        return apiRepo.getAllPhotos()
    }
}