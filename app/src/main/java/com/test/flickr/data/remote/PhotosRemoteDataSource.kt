package com.test.photoapp.core.data.remote.photos

import com.test.flickr.model.PhotosResponse

interface PhotosRemoteDataSource {
    suspend fun getPhotos(searchKey:String, apiKey: String): PhotosResponse<Any>
}