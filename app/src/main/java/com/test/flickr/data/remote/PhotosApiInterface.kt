package com.test.flickr.data.remote

import com.test.flickr.model.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotosApiInterface {
    @Headers("Content-Type: application/json")
    @GET(value = "?method=flickr.photos.search")
    suspend fun getPhotos(
        @Query(value = "api_key") api_key: String,
        @Query(value = "format") format: String = "json",
        @Query(value = "nojsoncallback") noCallback: String = "1",
        @Query(value = "text") searchKey: String,
        @Query(value = "extras") extras: String = "description,owner_name,date_taken"
    ): PhotosResponse<Any>
}