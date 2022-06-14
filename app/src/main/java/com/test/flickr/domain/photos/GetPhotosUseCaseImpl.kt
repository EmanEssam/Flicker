package com.test.flickr.domain.photos

import com.test.flickr.model.PhotosResponse
import com.test.flickr.data.repository.PhotosRepository
import javax.inject.Inject

class GetPhotosUseCaseImpl @Inject constructor(
    private val repository: PhotosRepository
) : GetPhotosUseCase {

    override suspend fun getPhotos(
        searchKey:String,apiKey: String
    ): PhotosResponse<Any> {
        return repository.getPhotos(searchKey,apiKey)
    }
}