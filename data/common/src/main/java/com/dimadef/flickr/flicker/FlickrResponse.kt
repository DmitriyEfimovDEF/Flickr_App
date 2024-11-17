package com.dimadef.flickr.flicker

import com.google.gson.annotations.SerializedName
import com.dimadef.flickr.model.ApiError

open class FlickrResponse<T>(
    open val data: T,
    @SerializedName("stat") internal open val status: String,
    @SerializedName("message") internal open val errorMassage: String?,
    @SerializedName("code") internal open val errorCode: Int?
) {
    private val isFailure get(): Boolean = status == "fail"

    fun getOrThrow(): T {
        if (isFailure) {
            throw ApiError(
                code = errorCode,
                throwable = Throwable(errorMassage)
            )
        }
        return data
    }
}
