package com.test.flicker.model

import com.test.flicker.model.Photos

data class PhotosResponse<T>(
    val photos: Photos,
    val stat: String
)