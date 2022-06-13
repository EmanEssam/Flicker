package com.test.flicker.domain.photos

import com.test.flicker.model.PhotosResponse

interface GetMoviesPhotosUseCase {
    suspend fun getMoviesPhotos(
        apiKey:String,
        page: Int,
        pageCount: Int
    ): PhotosResponse<Any>
}