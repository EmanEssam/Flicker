package com.test.flickr.data.repository

import com.test.flickr.model.PhotosResponse
import com.test.photoapp.core.data.remote.photos.PhotosRemoteDataSource
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val moviesRemoteDataSource: PhotosRemoteDataSource
) : PhotosRepository {
    override suspend fun getMoviesPhotos(searchKey:String,
        apiKey: String
    ): PhotosResponse<Any> {
        return moviesRemoteDataSource.getMoviesPhotos(searchKey,apiKey)
    }


}