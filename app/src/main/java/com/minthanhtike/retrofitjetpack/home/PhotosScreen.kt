package com.minthanhtike.retrofitjetpack.home

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.minthanhtike.retrofitjetpack.data.PhotoModels
import com.minthanhtike.retrofitjetpack.viewmodels.PhotosViewModel
import com.minthanhtike.retrofitjetpack.data.PhotoModelsItem


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PhotoScreen(photosViewModel: PhotosViewModel = hiltViewModel()) {
    val permissionRequest =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    LaunchedEffect(key1 = Unit){
        permissionRequest.launchPermissionRequest()
    }

    if (permissionRequest.status.isGranted) {
        val allPhotos = photosViewModel.allPhotos

        LaunchedEffect(Unit) {
            photosViewModel.gettingAllPhotos()
        }
        Log.d("PhotoScreenListSize", "PhotoScreen: ${allPhotos.size}")
        LazyColumn {
            if (allPhotos.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight(align = Alignment.CenterVertically)
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    )
                }
            }

            val photo=allPhotos.filter { photoModelsItem -> photoModelsItem.id==2 }
            Log.d("APIPHOTOS", "PhotoScreen: ${photo.size}")
            items(allPhotos) { item: PhotoModelsItem ->
                EachPhotos(photos = item)
            }


        }
    }


}

@Composable
fun EachPhotos(photos: PhotoModelsItem) {
    Card(
        shape = CutCornerShape(5.dp),
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        AsyncImage(model = photos.url, contentDescription = null)
    }
}