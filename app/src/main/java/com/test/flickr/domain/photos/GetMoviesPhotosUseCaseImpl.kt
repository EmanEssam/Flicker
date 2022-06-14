package com.test.flickr.domain.photos

import com.test.flickr.model.PhotosResponse
import com.test.flickr.data.repository.PhotosRepository
import javax.inject.Inject

class GetMoviesPhotosUseCaseImpl @Inject constructor(
    private val repository: PhotosRepository
) : GetMoviesPhotosUseCase {

    override suspend fun getMoviesPhotos(
        searchKey:String,apiKey: String
    ): PhotosResponse<Any> {
        return repository.getMoviesPhotos(searchKey,apiKey)
    }
}