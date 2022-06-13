package com.test.photoapp.core.data.remote.photos

import com.test.flicker.model.PhotosResponse

interface PhotosRemoteDataSource {
    suspend fun getMoviesPhotos(apiKey: String, page: Int, perPage: Int): PhotosResponse<Any>
}