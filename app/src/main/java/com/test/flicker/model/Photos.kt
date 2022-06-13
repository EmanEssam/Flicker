package com.test.flicker.model

import com.test.flicker.model.Photo


data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<Photo>,
    val total: Int
)