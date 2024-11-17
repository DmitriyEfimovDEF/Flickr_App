package com.dimadef.flickr.app.di

import com.dimadef.flickr.app.BuildConfig
import com.dimadef.flickr.model.BuildConfiguration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal class AppModule {
    @Provides
    fun provideBuildConfiguration(): BuildConfiguration {
        return BuildConfiguration(
            flickerApiKey = BuildConfig.FLICKR_API_KEY,
            isDebug = BuildConfig.DEBUG
        )
    }
}
