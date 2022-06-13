package com.test.flicker.domain.photos

import com.test.flicker.model.PhotosResponse
import com.test.flicker.data.repository.PhotosRepository
import javax.inject.Inject

class GetMoviesPhotosUseCaseImpl @Inject constructor(
    private val repository: PhotosRepository
) : GetMoviesPhotosUseCase {

    override suspend fun getMoviesPhotos(
        apiKey: String,
        page: Int,
        pageCount: Int
    ): PhotosResponse<Any> {
        return repository.getMoviesPhotos(apiKey, page, pageCount)
    }
}