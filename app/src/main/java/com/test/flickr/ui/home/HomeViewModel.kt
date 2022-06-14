package com.test.flickr.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.flickr.domain.photos.GetPhotosUseCase
import com.test.flickr.heloper.SingleLiveEvent
import com.test.flickr.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val photosUseCase: GetPhotosUseCase) :
    ViewModel() {
    val photosResponse = SingleLiveEvent<List<Photo>>()
    val photosError = SingleLiveEvent<String>()

    @ExperimentalCoroutinesApi
    fun getPhotos(searchKey: String = "a",apiKey:String) = viewModelScope.launch {
        try {
            photosUseCase.getPhotos(searchKey,apiKey).let {
                photosResponse.postValue(it.photos.photo)

            }
        } catch (e: Exception) {
            photosError.postValue(e.message)
        }
    }


}