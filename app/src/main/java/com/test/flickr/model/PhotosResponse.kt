package com.test.flickr.model

data class PhotosResponse<T>(
    val photos: Photos,
    val stat: String
)