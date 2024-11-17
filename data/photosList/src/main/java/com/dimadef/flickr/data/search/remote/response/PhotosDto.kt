package com.dimadef.flickr.data.search.remote.response

import com.google.gson.annotations.SerializedName
import com.dimadef.flickr.flicker.FlickrResponse
import com.dimadef.flickr.model.Page
import com.dimadef.flickr.remote.model.PhotoDto
import com.dimadef.flickr.remote.model.toPhoto

internal data class PhotosDto(
    @SerializedName("photo") val photos: List<PhotoDto>?,
    @SerializedName("page") val pageNumber: Int,
    @SerializedName("pages") val endPage: Int,
    @SerializedName("perpage") val pageSize: Int
)

internal class SearchResponseDto(
    @SerializedName("photos") override val data: PhotosDto,
    status: String,
    errorMassage: String?,
    errorCode: Int?
) : FlickrResponse<PhotosDto>(data, status, errorMassage, errorCode)

internal fun PhotosDto.toPagePhotos() = Page(
    items = photos?.map { it.toPhoto() }.orEmpty(),
    pageNumber = pageNumber - 1,
    pageSize = pageSize,
    hasNext = pageNumber < endPage
)
