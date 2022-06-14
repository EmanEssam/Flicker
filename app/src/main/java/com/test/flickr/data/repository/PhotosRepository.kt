package com.test.flickr.data.repository

import com.test.flickr.model.PhotosResponse

interface PhotosRepository {

    suspend fun getPhotos(searchKey: String, apiKey: String): PhotosResponse<Any>

}