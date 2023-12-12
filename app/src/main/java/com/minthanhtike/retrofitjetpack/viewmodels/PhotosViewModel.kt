package com.minthanhtike.retrofitjetpack.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.minthanhtike.retrofitjetpack.api.ApiService
import com.minthanhtike.retrofitjetpack.data.PhotoModels
import com.minthanhtike.retrofitjetpack.data.PhotoModelsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel(){
    var allPhotos :List<PhotoModelsItem> by mutableStateOf(listOf())

    init {
        gettingAllPhotos()
    }

    fun gettingAllPhotos(){
        viewModelScope.launch {
            try {
                allPhotos = apiService.getAllPhotos()
            }catch (e: Exception){
                e.stackTrace
            }

        }
    }
}