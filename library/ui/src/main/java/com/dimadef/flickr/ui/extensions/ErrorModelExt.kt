package com.dimadef.flickr.ui.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dimadef.flickr.library.ui.R
import com.dimadef.flickr.model.ApiError

val Throwable.localMessage
    @Composable get(): String {
        val genericMessage = stringResource(R.string.error_unexpected)
        val apiError = this as? ApiError ?: return genericMessage
        return when (apiError.type) {
            ApiError.Type.Network -> stringResource(R.string.error_noInternet)
            ApiError.Type.Server -> stringResource(R.string.error_serverUnavailable)
            ApiError.Type.Unreachable -> stringResource(R.string.error_serverUnreachable)
            ApiError.Type.Forbidden -> stringResource(R.string.error_serverAccessForbidden)
            ApiError.Type.UnAuthorized,
            ApiError.Type.BadRequest,
            ApiError.Type.NotFound,
            ApiError.Type.Client,
                -> apiError.message ?: genericMessage

            else -> genericMessage
        }
    }
