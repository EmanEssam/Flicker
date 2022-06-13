package com.test.flicker.data.repository

import com.test.flicker.model.PhotosResponse

interface PhotosRepository {

    suspend fun getMoviesPhotos(apiKey: String, page: Int, perPage: Int): PhotosResponse<Any>

}