package com.test.flicker.data.remote

import com.test.flicker.model.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotosApiInterface {
    @Headers("Content-Type: application/json")
    @GET(value = "?method=flickr.photos.getRecent")
    suspend fun getPhotos(
        @Query(value = "api_key") api_key: String,
        @Query(value = "format") format: String = "json",
        @Query(value = "page") page: Int,
        @Query(value = "per_page") per_page: Int,
        @Query(value = "nojsoncallback") no_json: Int = 1
    ): PhotosResponse<Any>

}