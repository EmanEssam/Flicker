package com.test.flickr.data.repository

import com.test.flickr.model.PhotosResponse
import com.test.photoapp.core.data.remote.photos.PhotosRemoteDataSource
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val photosRemoteDataSource: PhotosRemoteDataSource
) : PhotosRepository {
    override suspend fun getPhotos(searchKey:String,
                                   apiKey: String
    ): PhotosResponse<Any> {
        return photosRemoteDataSource.getPhotos(searchKey,apiKey)
    }


}