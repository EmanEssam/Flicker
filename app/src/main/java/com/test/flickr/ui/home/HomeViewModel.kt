package com.test.flickr.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.flickr.domain.photos.GetMoviesPhotosUseCase
import com.test.flickr.heloper.SingleLiveEvent
import com.test.flickr.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val getMoviesPhotosUseCase: GetMoviesPhotosUseCase) :
    ViewModel() {
    val photosResponse = SingleLiveEvent<List<Photo>>()
    val photosError = SingleLiveEvent<String>()

    @ExperimentalCoroutinesApi
    private val searchChanel = ConflatedBroadcastChannel<String>()
    fun getPhotos(searchKey: String = "a") = viewModelScope.launch {
        try {
            getMoviesPhotosUseCase.getMoviesPhotos(searchKey,"e184e6334420e5b4021827775adcf1fa").let {
                photosResponse.postValue(it.photos.photo)

            }
        } catch (e: Exception) {
            photosError.postValue(e.message)
        }
    }


}