package com.dimadef.flickr.model

data class Photo(
    val id: String,
    val title: String?,
    val description: String?,
    val url: String,
    val thumbnailUrl: String?
)
