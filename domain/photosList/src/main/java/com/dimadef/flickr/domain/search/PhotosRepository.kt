package com.dimadef.flickr.domain.search

import com.dimadef.flickr.model.Page
import com.dimadef.flickr.model.Photo

interface PhotosRepository {

    suspend fun getRecentPhotos(pageNumber: Int, pageSize: Int): Result<Page<Photo>>

}
