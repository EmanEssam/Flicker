package com.test.flickr.domain.photos

import com.test.flickr.model.PhotosResponse

interface GetPhotosUseCase {
    suspend fun getPhotos(searchKey:String,
                          apiKey:String

    ): PhotosResponse<Any>
}