package com.dimadef.flickr.data.search.repository

import com.dimadef.flickr.data.search.remote.datasource.PhotoRemoteDataSource
import com.dimadef.flickr.data.search.remote.response.PhotosDto
import com.dimadef.flickr.data.search.remote.response.toPagePhotos
import com.dimadef.flickr.domain.search.PhotosRepository
import com.dimadef.flickr.library.coroutines.CoroutineDispatchers
import com.dimadef.flickr.model.Page
import com.dimadef.flickr.model.Photo
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class DefaultPhotosRepository @Inject constructor(
    private val photosRemoteDataSource: PhotoRemoteDataSource,
    private val coroutineDispatchers: CoroutineDispatchers,
) : PhotosRepository {

    override suspend fun getRecentPhotos(
        pageNumber: Int,
        pageSize: Int,
    ): Result<Page<Photo>> = withContext(coroutineDispatchers.default) {
        photosRemoteDataSource.getRecent(limit = pageSize, page = pageNumber)
            .map(PhotosDto::toPagePhotos)
    }

}
