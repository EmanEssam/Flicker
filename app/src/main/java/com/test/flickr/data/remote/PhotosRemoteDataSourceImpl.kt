package com.test.flickr.data.remote

import com.test.flickr.model.PhotosResponse
import com.test.photoapp.core.data.remote.photos.PhotosRemoteDataSource
import javax.inject.Inject

class PhotosRemoteDataSourceImpl @Inject constructor(private val photosApiInterface: PhotosApiInterface) :
    PhotosRemoteDataSource {
    override suspend fun getPhotos(
        searchKey: String,
        apiKey: String

    ): PhotosResponse<Any> {
        return photosApiInterface.getPhotos(searchKey = searchKey, api_key = apiKey)
    }
}