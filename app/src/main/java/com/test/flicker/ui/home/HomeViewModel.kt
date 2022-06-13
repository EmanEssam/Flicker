package com.test.flicker.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.firebase.ui.auth.data.model.Resource
import com.test.flicker.domain.photos.GetMoviesPhotosUseCase
import com.test.flicker.heloper.SingleLiveEvent
import com.test.flicker.model.Photo
import com.test.flicker.ui.home.paging.MoviesPagingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val getMoviesPhotosUseCase: GetMoviesPhotosUseCase) :
    ViewModel() {
    val photosResponse = SingleLiveEvent<List<Photo>>()
    val photosError = SingleLiveEvent<String>()
    fun getPhotos() = viewModelScope.launch {
        try {
            getMoviesPhotosUseCase.getMoviesPhotos("e184e6334420e5b4021827775adcf1fa", 1, 10).let {
                photosResponse.postValue(it.photos.photo)

            }
        } catch (e: Exception) {
            photosError.postValue(e.message)
        }
    }
}