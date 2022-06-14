package com.test.flickr.domain.photos

import com.test.flickr.model.PhotosResponse

interface GetMoviesPhotosUseCase {
    suspend fun getMoviesPhotos(searchKey:String,
        apiKey:String

    ): PhotosResponse<Any>
}