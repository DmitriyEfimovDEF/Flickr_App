package com.dimadef.flickr.library.arch

import kotlinx.coroutines.flow.StateFlow

interface MviViewModel<Intent, State> {

    fun onIntent(intent: Intent)

    val state: StateFlow<State>
}
