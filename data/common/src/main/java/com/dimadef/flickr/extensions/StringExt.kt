package com.dimadef.flickr.extensions

fun String.orNullIfEmpty(): String? {
    return if (isNullOrEmpty()) null else this
}
