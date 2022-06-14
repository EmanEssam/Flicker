package com.test.flickr.utils

import android.util.Log

const val PHOTO_BASE_URL = "https://farm"
const val SLASH = "/"
const val UNDERSCORE = "_"
const val JPG = ".jpg"
const val size ="w"

object PhotoBuilder {
    fun getPhotoUrl(farm: Int, server: String, id: String, secret: String): String {
        val imageUrl =
            "$PHOTO_BASE_URL$farm.staticflickr.com/$server$SLASH$id$UNDERSCORE$secret$UNDERSCORE$size$JPG".trim()
        Log.d(
            "imageurl", imageUrl
        )
        return imageUrl
    }


}