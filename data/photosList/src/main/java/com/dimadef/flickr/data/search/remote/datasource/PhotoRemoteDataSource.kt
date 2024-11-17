package com.dimadef.flickr.data.search.remote.datasource

import com.dimadef.flickr.data.search.remote.response.PhotosDto
import com.dimadef.flickr.data.search.remote.service.FlickrService
import com.dimadef.flickr.model.BuildConfiguration
import javax.inject.Inject

internal class PhotoRemoteDataSource @Inject constructor(
    private val service: FlickrService,
    buildConfiguration: BuildConfiguration
) {

    private val apiKey = buildConfiguration.flickerApiKey

    suspend fun getRecent(limit: Int, page: Int): Result<PhotosDto> = service
        .getRecent(apiKey = apiKey, pageNumber = page + 1, limit = limit)
        .mapCatching { it.getOrThrow() }
}
