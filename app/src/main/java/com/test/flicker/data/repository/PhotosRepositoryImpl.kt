package com.test.flicker.data.repository

import com.test.flicker.data.repository.PhotosRepository
import com.test.flicker.model.PhotosResponse
import com.test.photoapp.core.data.remote.photos.PhotosRemoteDataSource
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: PhotosRemoteDataSource
) : PhotosRepository {
    override suspend fun getMoviesPhotos(
        apiKey: String,
        page: Int,
        perPage: Int
    ): PhotosResponse<Any> {
        return moviesRemoteDataSource.getMoviesPhotos(apiKey, page, perPage)
    }


}