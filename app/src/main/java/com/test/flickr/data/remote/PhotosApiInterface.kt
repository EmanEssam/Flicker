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
//    https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=e184e6334420e5b4021827775adcf1fa&text=h&format=json
//    https://www.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=e184e6334420e5b4021827775adcf1fa&format=json&page=1&per_page=10&nojsoncallback=1
}